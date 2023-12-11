package org.blogy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.blogy.entity.Role;
import org.blogy.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsAllByRole(Role role);
}
