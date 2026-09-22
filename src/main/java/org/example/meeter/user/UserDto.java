package org.example.meeter.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private UUID uuid;
    private String username;
    private String humanName;
    private String roleString;
}
