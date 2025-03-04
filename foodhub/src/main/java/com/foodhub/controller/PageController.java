package com.foodhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String home(){
        System.out.println("Inside Home page");
        return "index";
    }
}
