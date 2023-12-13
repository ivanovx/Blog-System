package org.blogy.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import org.blogy.entity.Setting;
import org.blogy.service.SettingService;

@Controller
@RequestMapping("/admin/settings")
public class AdminSettingController {
    private final SettingService settingService;

    public AdminSettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/{name}")
    public String getSetting(@PathVariable String name, Model model) {
        Setting setting = settingService.getSetting(name);

        model.addAttribute("setting", setting);

        return "admin/settings/index";
    }

    @PostMapping("/{name}")
    public String updateSetting(@PathVariable String name, @ModelAttribute Setting setting) {
        settingService.updateSetting(name, setting.getValue());

        return "redirect:/admin";
    }
}
