package org.example.meeter.place;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Size(min=-90, max=90, message = "Szerokość geograficzna może przyjmować wartości od -90 do 90.")
    private double latitude;
    @Size(min=-180, max=180, message = "Długość geograficzna może przyjmować wartości od -180 do 180.")
    private double longitude;
    private String category;
}
