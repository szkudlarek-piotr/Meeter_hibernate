package org.example.meeter.people;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.citybreak.Citybreak;
import org.example.meeter.cliques.Clique;
import org.example.meeter.event.Event;
import org.example.meeter.meeting.Meeting;
import org.example.meeter.place.Place;
import org.example.meeter.quote.Quote;
import org.example.meeter.songs.Song;
import org.example.meeter.visits.Visit;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="people")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid = UUID.randomUUID();
    private LocalDate birthdayDate;
    private String fbLink;

    @ManyToOne
    private Place home;
    private char gender;

    @OneToMany(mappedBy = "author")
    List<Quote> quotes;


    private List<Quote> getPublicQuotes() {
        return this.quotes.stream().filter(quote -> quote.isPublic()).toList();
    }

    @ManyToOne
    private Clique clique;

    private String livesIn;

    @ManyToMany(mappedBy = "meetingMembers")
    List<Meeting> humanMeetings;

    @ManyToMany(mappedBy = "humansInEvent")
    List<Event> humanEvents;

    @ManyToMany(mappedBy = "visitHumans")
    List<Visit> visits;

    @ManyToMany(mappedBy = "humansInTrip")
    List<Citybreak> humanTrips;

    @ManyToOne
    private Song song;

    public String getFullName() {
        return "%s %s".formatted(this.name, this.surname);
    }


    public HumanTileDto getHumanTileDto() {
        Random rand = new Random();
        HumanTileDto dto = new HumanTileDto();
        dto.setFullName(this.name + " " + this.surname);
        int numberOfQuotes = this.getPublicQuotes().size();
        if (numberOfQuotes > 0) {
            dto.setRandomQuote(this.getPublicQuotes().get(rand.nextInt(numberOfQuotes)).getQuote());
        }
        
        dto.setCliqueName(this.clique.getCliqueName());
        dto.setNumberOfVisits(this.visits.size());
        return dto;
    }

}
