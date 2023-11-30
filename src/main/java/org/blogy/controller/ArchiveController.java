package org.blogy.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.blogy.entity.Article;
import org.blogy.service.ArchiveService;

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping("/{month}/{year}")
    public String index(@PathVariable int month, @PathVariable int year, Model model) {
        List<Article> articles = archiveService.get(month, year);

        model.addAttribute("articles", articles);

        return "home/archive";
    }
}
