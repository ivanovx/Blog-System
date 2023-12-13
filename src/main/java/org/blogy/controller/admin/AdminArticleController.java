package org.blogy.controller.admin;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.blogy.entity.Article;
import org.blogy.entity.Category;
import org.blogy.request.ArticleRequest;
import org.blogy.service.ArticleService;
import org.blogy.service.CategoryService;

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

        return "redirect:/admin/articles";
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

        return "redirect:/admin/articles";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        articleService.delete(id);

        return "redirect:/admin/articles";
    }
}
