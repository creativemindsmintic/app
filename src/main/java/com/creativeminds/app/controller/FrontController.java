package com.creativeminds.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }
}
