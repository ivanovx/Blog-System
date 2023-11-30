package org.ivanovx.bloggable.service;

import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public long count() {
        return this.categoryRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Category getCategory(String name) {
        return this.categoryRepository.findByName(name).orElseThrow();
    }

    public Category createCategory(Category category){
        return this.categoryRepository.save(category);
    }

    public Category updateCategory(long id, String categoryName) {
        Category category = this.getCategory(id);

        category.setName(categoryName);
        category.setModified(LocalDateTime.now());

        return this.categoryRepository.save(category);
    }
}
