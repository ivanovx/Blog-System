package org.ivanovx.bloggable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ivanovx.bloggable.inputModel.LoginModel;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginModel());

        return "user/login";
    }
}