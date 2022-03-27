package com.banking.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String bank(Model model){
        model.addAttribute("title","Банк");
        return "bank";
    }


}
