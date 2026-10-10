package org.example.meeter.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findUsersByUsername(String username);

    public Optional<User> findUserByUsername(String username);
}
