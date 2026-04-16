package com.urbanclone.urbanclonebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "UrbanClone Backend is Running 🚀";
    }
}