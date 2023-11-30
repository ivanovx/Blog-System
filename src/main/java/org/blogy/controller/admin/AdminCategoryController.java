package org.blogy.controller.admin;

import java.util.List;

import org.blogy.entity.Category;
import org.blogy.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);

        return "admin/categories/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());

        return "admin/categories/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category) {
        categoryService.createCategory(category);

        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long id, Model model) {
        Category category = categoryService.getCategory(id);

        model.addAttribute("category", category);

        return "admin/categories/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute Category category) {
        categoryService.updateCategory(id, category.getName());

        return "redirect:/admin";
    }
}
