package org.example.meeter.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.example.meeter.userRoles.UserRole;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String username;

    @ManyToOne
    private Human human;

    @Email
    private String email;

    private String passwordHash;

    @ManyToMany
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private Set<UserRole> userRoles = new HashSet<>();

    UUID uuid = UUID.randomUUID();

    @CurrentTimestamp
    LocalDateTime creationDate;

    public void addRole(UserRole role) {
        this.userRoles.add(role);
    }


    public UserDto getDto() {
        UserDto dto = new UserDto();
        dto.setUsername(this.getUsername());
//        dto.setRoleString(this.userRole.getRoleName());
        dto.setUuid(this.uuid);
        dto.setHumanName(this.human.getFullName());
        return dto;
    }


}
