package org.ivanovx.bloggable.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public long count() {
        return categoryRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Category getCategory(String name) {
        return categoryRepository.findByName(name).orElseThrow();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(long id, String categoryName) {
        Category category = getCategory(id);

        category.setName(categoryName);
        category.setModified(LocalDateTime.now());

        return this.categoryRepository.save(category);
    }
}
