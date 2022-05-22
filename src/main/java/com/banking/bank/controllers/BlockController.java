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
    @GetMapping("/register") //гетмап регистрации
    public String regIn(Model model){
        model.addAttribute("userForm", new UserAcc()); //TODO сделать проверку на сессию
        return "registerPage";
    }
    @PostMapping("/register") //постмап регистрации, тут создаётся сессия
    public String reg(@ModelAttribute("userForm") UserAcc userForm, HttpSession session, Model model){
        try {
            userService.createUser(userForm);
            UserAcc user = userService.find(userForm.getEmail());
            session.setAttribute("infoUser", user);
            return "registerPage";
        }catch (Exception e){
            model.addAttribute("exception", e.getMessage());
            return "ErrorPage";
        }
    }
    @GetMapping("/login") //гетмап логина(по хорошему сделать проверку сессии на регистрацию такую же)
    public String signIn(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("userForm", new UserAcc());
            return "loginPage";
        }else return "alreadyLoggedError";
    }
    @PostMapping("/login") //постмап логина, созда1тся сессия
    public String signCheck(@ModelAttribute("userForm") UserAcc userForm, HttpSession session){
        if(userService.checkUser(userForm) == true) {
            UserAcc user = userService.find(userForm.getEmail());
            session.setAttribute("infoUser", user);
            return "redirect:/";
        }else return "redirect:/login";
    }
    @GetMapping("/exit") //геммап выхода, кидает на логин
    public String logOut(Model model, HttpSession session){
        if(session.getAttribute("infoUser") != null) {
            session.removeAttribute("infoUser");
        }
        return "redirect:/login";
    }
}
