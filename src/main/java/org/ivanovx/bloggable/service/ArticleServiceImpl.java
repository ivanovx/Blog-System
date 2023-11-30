package org.ivanovx.bloggable.service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.ivanovx.bloggable.entity.Article;
import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.util.SlugGenerator;
import org.ivanovx.bloggable.request.ArticleRequest;
import org.ivanovx.bloggable.repository.ArticleRepository;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    private final CategoryService categoryService;

    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    public long count() {
        return articleRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Article> getArticles() {
        return articleRepository.findByOrderByCreatedDesc();
    }

    @Transactional(readOnly = true)
    public Page<Article> getArticles(Pageable pageable) {
        return articleRepository.findByOrderByCreatedDesc(pageable);
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByCategory(String categoryName) {
        return articleRepository.findAllByCategoryName(categoryName);
    }

    @Transactional(readOnly = true)
    public Article getArticle(long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Article getArticle(String slug) {
        return articleRepository.findBySlug(slug).orElseThrow();
    }

    public Article createArticle(ArticleRequest model) {
        Category category = categoryService.getCategory(model.getCategory());

        Article article = new Article();

        article.setCategory(category);
        article.setTitle(model.getTitle());
        article.setContent(model.getContent());
        article.setSlug(SlugGenerator.toSlug(model.getTitle()));

        Set<String> keywords = Arrays.stream(model.getKeywords().split(",")).collect(Collectors.toSet());

        article.setKeywords(keywords);

        return articleRepository.save(article);
    }

    public Article updateArticle(long id, ArticleRequest model) {
        Article article = getArticle(id);
        Category category = categoryService.getCategory(model.getCategory());
        Set<String> keywords = Arrays.stream(model.getKeywords().split(",")).collect(Collectors.toSet());

        article.setCategory(category);
        article.setContent(model.getContent());
        article.setTitle(model.getTitle());
        article.setModified(LocalDateTime.now());
        article.setKeywords(keywords);

        return articleRepository.save(article);
    }
}
