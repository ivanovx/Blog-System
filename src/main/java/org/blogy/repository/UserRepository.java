package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.blogy.entity.Role;
import org.blogy.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);

    boolean existsAllByRole(Role role);
}
