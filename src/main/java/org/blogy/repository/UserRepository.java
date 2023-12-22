package org.blogy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.blogy.entity.Role;
import org.blogy.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsAllByRole(Role role);
}
