package org.ivanovx.bloggable.controller.admin;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.ivanovx.bloggable.service.ArticleService;
import org.ivanovx.bloggable.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ArticleService articleService;

    private final CategoryService categoryService;

    public AdminController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    // TODO
    // Default password change
    @GetMapping
    public String index(Model model) {
        long articlesCount = this.articleService.count();
        long categoriesCount = this.categoryService.count();

        model.addAttribute("articlesCount", articlesCount);
        model.addAttribute("categoriesCount", categoriesCount);

        return "admin/index";
    }
}
