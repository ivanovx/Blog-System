package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.blogy.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();

    Optional<Category> findByName(String name);
}
