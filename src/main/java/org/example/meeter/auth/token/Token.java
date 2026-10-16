package org.example.meeter.auth.token;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.auth.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="tokens")
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String authToken;

    private LocalDateTime creationTime;

    private LocalDateTime expireTime;

    @ManyToOne(optional = false)
    private User tokenOwner;

}
