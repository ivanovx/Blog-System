package org.blogy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.blogy.entity.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>, CrudRepository<Article, Long> {

    List<Article> findAll();

    List<Article> findByOrderByCreatedDesc();

    Page<Article> findByOrderByCreatedDesc(Pageable pageable);

    List<Article> findAllByCategoryName(String name);

    Optional<Article> findById(long id);

    Optional<Article> findBySlug(String slug);
}