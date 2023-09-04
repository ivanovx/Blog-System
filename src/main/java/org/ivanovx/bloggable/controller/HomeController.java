package org.ivanovx.bloggable.controller;

import org.ivanovx.bloggable.entity.Article;
import org.ivanovx.bloggable.service.ArticleService;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String index(Pageable pageable, Model model) {
        Page<Article> page = this.articleService.getArticles(pageable);

        model.addAttribute("page", page);

        return "home/index";
    }

    @GetMapping("/articles/{slug}")
    public String article(@PathVariable String slug, Model model) {
        Article article = this.articleService.getArticle(slug);

        model.addAttribute("article", article);

        return "home/article";
    }
}