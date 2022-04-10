package com.banking.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String bank(Model model, HttpSession session){
        model.addAttribute("title", "Банк");
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("attention", "Вам нужно залогиниться!");
            return "bank";
        }else{
            return "bankLogged";
        }
    }
    @GetMapping("/support")
    public String support(){
        return "supportPage";
    }
    @GetMapping("/deposit")
    public String deposit(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("title", "Банк");
            return "bank";
        }
        else{
            return "depositPage";
        }
    }

    @GetMapping("/transfer")
    public String transfer(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("title", "Банк");
            return "bank";
        }
        else{
            return "transferPage";
        }
    }

}
