package org.ivanovx.bloggable.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.ivanovx.bloggable.entity.User;
import org.ivanovx.bloggable.entity.Role;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);
}
