package org.ivanovx.bloggable.controller;

import java.util.Map;
import java.util.List;
import java.time.YearMonth;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.ivanovx.bloggable.entity.Category;
import org.ivanovx.bloggable.service.ArticleService;
import org.ivanovx.bloggable.service.CategoryService;
import org.ivanovx.bloggable.service.SettingService;

@ControllerAdvice
public class GlobalController {
    private final ArticleService articleService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    public GlobalController(ArticleService articleService, SettingService settingService, CategoryService categoryService) {
        this.articleService = articleService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return this.categoryService.getCategories();
    }

    @ModelAttribute("settings")
    public Map<String, String> settings() {
        return this.settingService.settingsMap();
    };

    @ModelAttribute("archive")
    public Map<YearMonth, Long> archive() {
        return articleService.createArchive();
    }
}