package org.blogy.controller;

import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.blogy.entity.Article;
import org.blogy.service.ArticleService;

@Controller
@RequestMapping(value = {"/", "/articles" })
public class HomeController {
    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String articles(Pageable pageable, Model model) {
        Page<Article> page = articleService.getArticles(pageable);

        model.addAttribute("page", page);

        return "home/index";
    }

    @GetMapping("/{slug}")
    public String article(@PathVariable String slug, Model model) {
        Article article = articleService.getArticle(slug);

        model.addAttribute("article", article);

        return "home/article";
    }
}