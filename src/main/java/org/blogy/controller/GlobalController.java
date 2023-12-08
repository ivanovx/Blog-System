package org.blogy.controller;

import java.util.Map;
import java.util.List;
import java.time.YearMonth;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.blogy.entity.Category;
import org.blogy.service.ArchiveService;
import org.blogy.service.CategoryService;
import org.blogy.service.SettingService;

@ControllerAdvice
public class GlobalController {
    private final ArchiveService archiveService;

    private final SettingService settingService;

    private final CategoryService categoryService;

    public GlobalController(ArchiveService archiveService, SettingService settingService, CategoryService categoryService) {
        this.archiveService = archiveService;
        this.settingService = settingService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getCategories();
    }

    @ModelAttribute("settings")
    public Map<String, String> settings() {
        return settingService.settingsMap();
    };

    @ModelAttribute("archive")
    public Map<YearMonth, Long> archive() {
        return archiveService.create();
    }
}