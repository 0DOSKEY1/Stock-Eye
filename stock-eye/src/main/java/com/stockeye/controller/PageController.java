package com.stockeye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/error")
    public String error() {
        return "Oh now an error ocurred!";
    }
}

