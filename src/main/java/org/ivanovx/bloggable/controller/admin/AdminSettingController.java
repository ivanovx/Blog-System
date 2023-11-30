package org.ivanovx.bloggable.controller.admin;


import org.ivanovx.bloggable.entity.User;
import org.ivanovx.bloggable.entity.Setting;
import org.ivanovx.bloggable.service.SettingService;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/settings")
public class AdminSettingController {
    private final SettingService settingService;

    public AdminSettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    // TODO
    @GetMapping
    public String index(Model model, Principal principal) {
        User user = (User) principal;

        List<Setting> settings = this.settingService.getSettings();

        model.addAttribute("settings", settings);
        model.addAttribute("user", user);

        return "admin/settings";
    }

    @PostMapping("/{id}")
    public String updateSetting(@PathVariable long id, @RequestParam("value") String value) {
        this.settingService.updateSetting(id, value);

        return "redirect:/";
    }
}
