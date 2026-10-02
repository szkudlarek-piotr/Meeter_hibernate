package org.example.meeter.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class HumanDtoForInteraction {
    private String fullName;
    private UUID uuid;
}
