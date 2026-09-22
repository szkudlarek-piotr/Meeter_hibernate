package org.example.meeter.quote;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=4, max=10240, message = "Cytat powinien mieć od 2 do 10240 znaków.")
    private String quote;

    @ManyToOne
    private Human author;
    private boolean isPublic;

    @CurrentTimestamp
    private LocalDateTime addTime;

    @Column(nullable = false, unique = true, updatable = false)
    private java.util.UUID uuid = UUID.randomUUID();
}
