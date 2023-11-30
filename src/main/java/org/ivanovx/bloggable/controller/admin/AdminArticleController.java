package org.ivanovx.bloggable.controller.admin;

import java.util.List;

import org.ivanovx.bloggable.entity.Article;
import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.request.ArticleRequest;
import org.ivanovx.bloggable.service.ArticleService;
import org.ivanovx.bloggable.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/articles")
public class AdminArticleController {
    private final ArticleService articleService;

    private final CategoryService categoryService;

    public AdminArticleController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Article> articles = articleService.getArticles();

        model.addAttribute("articles", articles);

        return "admin/articles/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("article", new ArticleRequest());

        return "admin/articles/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ArticleRequest request) {
        articleService.createArticle(request);

        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long id, Model model) {
        Article article = articleService.getArticle(id);
        List<Category> categories = categoryService.getCategories();

        ArticleRequest articleModel = ArticleRequest.of(article);

        model.addAttribute("articleId", article.getId());
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleModel);

        return "admin/articles/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute ArticleRequest articleModel) {
        articleService.updateArticle(id, articleModel);

        return "redirect:/admin";
    }
}
