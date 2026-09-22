package org.example.meeter.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class HumanTileDto {
    private String fullName;
    private String cliqueName;
    private String randomQuote;
    private int numberOfVisits;

}
