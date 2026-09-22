package org.example.meeter.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.example.meeter.userRoles.UserRole;

import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    private Human human;

    @Email
    private String email;

    private String passwordHash;

    @ManyToOne
    private UserRole userRole;

    UUID uuid = UUID.randomUUID();


    public UserDto getDto() {
        UserDto dto = new UserDto();
        dto.setUsername(this.getUsername());
        dto.setRoleString(this.userRole.getRoleName());
        dto.setUuid(this.uuid);
        dto.setHumanName(this.human.getFullName());
        return dto;
    }


}
