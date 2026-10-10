package org.example.meeter.auth.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.meeter.auth.token.Token;
import org.example.meeter.auth.token.TokenRepository;
import org.example.meeter.auth.user.User;
import org.example.meeter.auth.userDetailsService.CurrentUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;

    private String getTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if ("authToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenString = getTokenFromCookie(request);

        Token token = tokenRepository.findByAuthToken(tokenString).orElse(null);

        if (token != null) {
            User user = token.getTokenOwner();

            Set<SimpleGrantedAuthority> authorities = user.getUserRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                    .collect(Collectors.toSet());
            CurrentUser currentUser = new CurrentUser(user, authorities);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            currentUser,
                            null,
                            authorities
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
