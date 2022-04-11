package com.banking.bank.controllers;

import com.banking.bank.models.UserAcc;
import com.banking.bank.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    UserDetailsServiceImpl userService;
    @GetMapping("/")
    public String bank(Model model, HttpSession session){
        model.addAttribute("title", "Банк");
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("attention", "Вам нужно залогиниться!");
            return "bank";
        }else{
            UserAcc finalUserAcc = (UserAcc)session.getAttribute("infoUser");
            model.addAttribute("email", finalUserAcc.getEmail() );
            model.addAttribute("balance", finalUserAcc.getBalance());
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

            model.addAttribute("sum", new String());
            UserAcc finalUserAcc = (UserAcc)session.getAttribute("infoUser");
            model.addAttribute("email", finalUserAcc.getEmail() );
            model.addAttribute("balance", finalUserAcc.getBalance());
            return "depositPage";
        }
    }
    @PostMapping("/deposit")
    public String depositValue(@ModelAttribute("sum") String sum, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            return "bank";
        }
        else{
            try {
                Long value = Long.parseLong(sum);
                UserAcc userAcc = (UserAcc) session.getAttribute("infouser");
                userService.deposit(userAcc.getEmail(),value);
                return "redirect:/";
            }catch (Exception e){
                return "ErrorPage";
            }
        }
    }
    @GetMapping("/transfer")
    public String transfer(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("title", "Банк");
            return "bank";
        }
        else{
            UserAcc finalUserAcc = (UserAcc)session.getAttribute("infoUser");
            model.addAttribute("email", finalUserAcc.getEmail() );
            model.addAttribute("balance", finalUserAcc.getBalance());
            return "transferPage";
        }
    }

}
