package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // HTML atau JSON
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("isActive", "dashboard");
        return "index";
    }

}