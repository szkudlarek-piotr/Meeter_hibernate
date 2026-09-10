package org.example.meeter.cliques;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.place.Place;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="cliques")
public class Clique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=32, message = "Nazwa kliki powinna mieć od 2 do 32 znaków.")
    private String cliqueName;

    @ManyToOne
    private Place cliqueCapital;

}
