package org.ivanovx.bloggable.service;

import org.ivanovx.bloggable.entity.Article;
import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.repository.ArticleRepository;
import org.ivanovx.bloggable.request.ArticleRequest;
import org.ivanovx.bloggable.util.SlugGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

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
        return this.articleRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Article> getArticles() {
        return this.articleRepository.findByOrderByCreatedDesc();
    }

    @Transactional(readOnly = true)
    public Page<Article> getArticles(Pageable pageable) {
        return this.articleRepository.findByOrderByCreatedDesc(pageable);
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByCategory(String categoryName) {
        return this.articleRepository.findAllByCategoryName(categoryName);
    }

    @Transactional(readOnly = true)
    public Article getArticle(long id) {
        Article article = this.articleRepository.findById(id).orElseThrow();

        return article;
    }

    @Transactional(readOnly = true)
    public Article getArticle(String slug) {
        return this.articleRepository.findBySlug(slug).orElseThrow();
    }

    public Article createArticle(ArticleRequest model) {
        Category category = this.categoryService.getCategory(model.getCategory());

        Article article = new Article();

        article.setCategory(category);
        article.setTitle(model.getTitle());
        article.setContent(model.getContent());
       // article.setAuthor(UserUtil.getActiveUser());
        article.setSlug(SlugGenerator.toSlug(model.getTitle()));

        Set<String> keywords = Arrays.stream(model.getKeywords().split(",")).collect(Collectors.toSet());

        article.setKeywords(keywords);

        return this.articleRepository.save(article);
    }

    public Article updateArticle(long id, ArticleRequest model) {
        Article article = this.articleRepository.findById(id).orElseThrow();
        Category category = this.categoryService.getCategory(model.getCategory());
        Set<String> keywords = Arrays.stream(model.getKeywords().split(",")).collect(Collectors.toSet());

        article.setCategory(category);
        article.setContent(model.getContent());
        article.setTitle(model.getTitle());
        article.setModified(LocalDateTime.now());
        article.setKeywords(keywords);

        return this.articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> getArchive(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);

        List<Article> articles = this.getArticles()
                .stream()
                .filter(article -> YearMonth.from(article.getCreated()).compareTo(yearMonth) == 0)
                .toList();

        return articles;
    }

    @Transactional(readOnly = true)
    public Map<YearMonth, Long> createArchive() {
        return this.getArticles()
                .stream()
                .collect(Collectors.groupingBy(article -> YearMonth.from(article.getCreated()),
                        HashMap::new,
                        Collectors.counting()
                ));
    }
}
