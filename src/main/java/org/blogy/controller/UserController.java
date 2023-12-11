package org.blogy.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.blogy.request.LoginRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginRequest());

        return "user/login";
    }
}