package org.example.meeter.interactions.weddings;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="weddings")
public class Wedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Human groom;
    @OneToOne
    private Human bride;

    private LocalDateTime dateTime;

    @OneToOne
    private Place ceremonyPlace;
    @OneToOne
    private Place partyPlace;

    @OneToOne
    private Place hotel;

    @Size(min=16, max=10240)
    private String longDesc;

    @ManyToMany
    @JoinTable(
            name="wedding_guest",
            joinColumns = @JoinColumn(name="wedding_id"),
            inverseJoinColumns = @JoinColumn(name="human_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"wedding_id", "human_id"},
                    name = "unique_human_weddingGuest"
            )
    )
    private List<Human> guests;

    private boolean hadAfter;

    private UUID uuid;
}
