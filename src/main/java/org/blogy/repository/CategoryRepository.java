package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.blogy.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();

    Optional<Category> findByName(String name);
}
