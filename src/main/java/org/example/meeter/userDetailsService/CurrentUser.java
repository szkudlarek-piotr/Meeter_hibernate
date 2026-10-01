package org.example.meeter.userDetailsService;

import lombok.Getter;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.example.meeter.user.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class CurrentUser implements UserDetails {
    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public CurrentUser(User user, Collection<? extends GrantedAuthority> authorities)  {
        this.user = user;
        this.authorities = authorities;
    }
    public Human getHuman() {
        return user.getHuman();
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
