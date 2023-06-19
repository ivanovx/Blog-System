package org.ivanovx.bloggable.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import org.ivanovx.bloggable.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();

    Optional<Category> findByName(String name);
}
