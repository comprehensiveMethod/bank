package com.banking.bank.controllers;

import com.banking.bank.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlockController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/register")
    public String signIn(Model model){
        return "registerPage";
    }
}
