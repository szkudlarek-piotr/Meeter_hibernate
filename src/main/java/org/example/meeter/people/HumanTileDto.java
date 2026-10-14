package org.example.meeter.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class HumanTileDto {
    private String fullName;
    private String cliqueName;
    private String randomQuote;
    private String photoLink;
    private int numberOfVisits;
    private int otherInteractions;



}
