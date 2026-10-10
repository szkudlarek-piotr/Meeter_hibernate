package org.example.meeter.auth.authorization;

import lombok.RequiredArgsConstructor;

import org.example.meeter.auth.token.Token;
import org.example.meeter.auth.token.TokenRepository;
import org.example.meeter.auth.user.User;
import org.example.meeter.auth.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Value("${app.auth.token.lifetime-seconds}")
    private Long tokenLifetimeInSeconds; // Naprawiona literówka w nazwie

    public Token createToken(User user) {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        String tokenString = HexFormat.of().formatHex(bytes);

        LocalDateTime now = LocalDateTime.now();
        Token token = new Token();
        token.setAuthToken(tokenString);
        token.setCreationTime(now);
        token.setExpireTime(now.plusSeconds(tokenLifetimeInSeconds));
        token.setTokenOwner(user);

        return tokenRepository.save(token);
    }

    public Token login(String username, String password) {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika."));

        // POPRAWKA: Dodano "!", bo chcemy rzucić wyjątek, gdy hasła się NIE zgadzają
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BadCredentialsException("Nieprawidłowy username lub hasło.");
        }

        return createToken(user); // Reużywamy gotową metodę
    }
}