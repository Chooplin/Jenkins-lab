package xyz.chuplin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("msg", "Hello, World");
        model.addAttribute("today", new Date());
        System.out.println(model.getAttribute("today"));
        return "index";
    }
}
