package org.example.meeter.interactions.flatmates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.auth.user.User;
import org.example.meeter.people.Human;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Flatmate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="human_id")
    private Human Human;

    private LocalDate startDate;

    private LocalDate stopDate;

    @ManyToOne
    private User addedBy;
}
