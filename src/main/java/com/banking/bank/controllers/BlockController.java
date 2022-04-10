package com.banking.bank.controllers;

import com.banking.bank.models.UserAcc;
import com.banking.bank.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BlockController {
    @Autowired
    UserDetailsServiceImpl userService;
    @GetMapping("/register")
    public String regIn(Model model){
        model.addAttribute("userForm", new UserAcc());
        return "registerPage";
    }
    @PostMapping("/register")
    public String reg(@ModelAttribute("userForm") UserAcc userForm, HttpSession session){
        userService.createUser(userForm);
        session.setAttribute("infoUser", userForm);
        return "registerPage";
    }
    @GetMapping("/login")
    public String signIn(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("userForm", new UserAcc());
            return "loginPage";
        }else return "alreadyLoggedError";
    }
    @PostMapping("/login")
    public String signCheck(@ModelAttribute("userForm") UserAcc userForm, HttpSession session){
        if(userService.checkUser(userForm) == true);
        session.setAttribute("infoUser", userForm);
        return "redirect:/";
    }
    @GetMapping("/exit")
    public String logOut(Model model, HttpSession session){
        if(session.getAttribute("infoUser") != null) {
            session.removeAttribute("infoUser");
        }
        return "redirect:/login";
    }
}
