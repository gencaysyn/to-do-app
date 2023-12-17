package com.gencaysyn.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String getIndex() {
        return "<h1> Merhaba! </h1>";
    }
}
