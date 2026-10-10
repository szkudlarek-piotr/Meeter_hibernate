package org.example.meeter.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("select t from Token t where t.authToken = ?1 AND CURRENT_TIMESTAMP between t.creationTime and t.expireTime")
    Optional<Token> findByAuthToken(String authToken);
}
