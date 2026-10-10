package org.example.meeter.auth.authorization;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.meeter.auth.token.Token;
import org.example.meeter.auth.userDetailsService.CurrentUser;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Token token = authService.createToken(currentUser.getUser());

        long maxAge = java.time.Duration.between(
                LocalDateTime.now(),
                token.getExpireTime()
        ).getSeconds();

        ResponseCookie cookie = ResponseCookie
                .from("authToken", token.getAuthToken())
                .httpOnly(true)
                .secure(request.isSecure())
                .path("/")
                .sameSite("Lax")
                .maxAge(Math.max(0, maxAge))
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        response.sendRedirect("/");

    }
}
