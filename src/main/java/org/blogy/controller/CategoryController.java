package org.blogy.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.blogy.entity.Article;
import org.blogy.service.ArticleService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final ArticleService articleService;

    public CategoryController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{name}")
    public String index(@PathVariable String name, Model model) {
        List<Article> articles = articleService.getArticlesByCategory(name);

        model.addAttribute("articles", articles);

        return "home/category";
    }
}
