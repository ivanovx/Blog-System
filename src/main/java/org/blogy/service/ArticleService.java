package org.blogy.service;

import java.util.List;

import org.blogy.entity.Article;
import org.blogy.form.ArticleForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ArticleService {
    long count();

    List<Article> getArticles();

    Page<Article> getArticles(Pageable pageable);

    List<Article> getArticles(String categoryName);

    Article getArticle(long id);

    Article getArticle(String slug);

    Article createArticle(ArticleForm form);

    Article updateArticle(long id, ArticleForm form);

    void delete(long id);
}