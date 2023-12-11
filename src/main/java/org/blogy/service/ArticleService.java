package org.blogy.service;

import java.util.List;

import org.blogy.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.blogy.request.ArticleRequest;

public interface ArticleService {
    long count();

    List<Article> getArticles();

    Page<Article> getArticles(Pageable pageable);

    List<Article> getArticlesByCategory(String categoryName);

    Article getArticle(long id);

    Article getArticle(String slug);

    Article createArticle(ArticleRequest model);

    Article updateArticle(long id, ArticleRequest model);

    void delete(long id);
}