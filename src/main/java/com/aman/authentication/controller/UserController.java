package com.aman.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aman.authentication.model.User;
import com.aman.authentication.service.UserService;

@Controller
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.saveUser(user);
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}