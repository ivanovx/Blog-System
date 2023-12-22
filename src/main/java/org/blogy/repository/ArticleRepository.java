package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.blogy.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedDesc();

    Page<Article> findAllByOrderByCreatedDesc(Pageable pageable);

    List<Article> findAllByCategoryName(String name);

    //Optional<Article> findById(long id);

    Optional<Article> findBySlug(String slug);

    boolean existsBySlug(String slug);
}