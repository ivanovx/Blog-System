package org.blogy.controller.admin;

import org.blogy.entity.Setting;
import org.blogy.service.SettingService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/admin/settings")
public class AdminSettingController {
    private final SettingService settingService;

    public AdminSettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public String index(Model model) {
        List<Setting> settings = settingService.getSettings();

        model.addAttribute("allSettings", settings);

        return "admin/settings";
    }

   /* @PostMapping("/{id}")
    public String updateSetting(@PathVariable long id, @RequestParam("value") String value) {
        settingService.updateSetting(id, value);

        return "redirect:/admin/settings";
    }*/
}
