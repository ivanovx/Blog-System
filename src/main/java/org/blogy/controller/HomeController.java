package org.blogy.controller;


import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import org.blogy.entity.Article;
import org.blogy.service.ArticleService;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String articles(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 3);
        Page<Article> articlesPage = articleService.getArticles(pageable);

        model.addAttribute("page", articlesPage);

        return "home/index";
    }

    @GetMapping("/articles/{slug}")
    public String article(@PathVariable String slug, Model model) {
        Article article = articleService.getArticle(slug);

        model.addAttribute("article", article);

        return "home/article";
    }
}