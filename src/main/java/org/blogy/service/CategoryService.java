package org.blogy.service;

import java.util.List;

import org.blogy.entity.Category;

public interface CategoryService {
    long count();

    List<Category> getCategories();

    Category getCategory(Long id);

    Category getCategory(String name);

    Category createCategory(Category category);

    Category updateCategory(long id, String categoryName);
}