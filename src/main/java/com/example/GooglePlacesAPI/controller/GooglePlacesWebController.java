package com.example.GooglePlacesAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GooglePlacesWebController {
    @GetMapping("/")
    public String getHomePage(){return "home";}
    //@GetMapping("/landmark")
}
