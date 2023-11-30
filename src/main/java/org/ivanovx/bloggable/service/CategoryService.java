package org.ivanovx.bloggable.service;

import java.util.List;

import org.ivanovx.bloggable.entity.Category;

public interface CategoryService {
    long count();

    List<Category> getCategories();

    Category getCategory(Long id);

    Category getCategory(String name);

    Category createCategory(Category category);

    Category updateCategory(long id, String categoryName);
}