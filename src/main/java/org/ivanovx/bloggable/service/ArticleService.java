package org.ivanovx.bloggable.service;

import java.util.*;
import java.time.YearMonth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.ivanovx.bloggable.entity.Article;
import org.ivanovx.bloggable.request.ArticleRequest;

public interface ArticleService {
    long count();

    List<Article> getArticles();

    Page<Article> getArticles(Pageable pageable);

    List<Article> getArticlesByCategory(String categoryName);

    Article getArticle(long id);

    Article getArticle(String slug);

    Article createArticle(ArticleRequest model);

    Article updateArticle(long id, ArticleRequest model);

    List<Article> getArchive(int month, int year);

    Map<YearMonth, Long> createArchive();
}