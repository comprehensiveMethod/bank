package com.banking.bank.controllers;

import com.banking.bank.models.Deposit;
import com.banking.bank.models.Transfer;
import com.banking.bank.models.UserAcc;
import com.banking.bank.repository.UserAccRepository;
import com.banking.bank.service.DepositService;
import com.banking.bank.service.TransferService;
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
    //Автопривязки
    @Autowired
    UserAccRepository userAccRepository;
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    DepositService depositService;
    @Autowired
    TransferService transferService;
    @GetMapping("/") //Основная страница(проверка на сессию)
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
    @GetMapping("/support")//страница саппорта
    public String support(){
        return "supportPage";
    }
    @GetMapping("/deposit") //гетмап депозита
    public String deposit(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("title", "Банк");
            return "bank";
        }
        else{
            model.addAttribute("deposit", new Deposit());
            UserAcc finalUserAcc = (UserAcc)session.getAttribute("infoUser");
            model.addAttribute("email", finalUserAcc.getEmail() );
            model.addAttribute("balance", finalUserAcc.getBalance());
            return "depositPage";
        }
    }
    @PostMapping("/deposit")//постмап депозита, выполняется пополнение счёта акка
    public String depositValue(@ModelAttribute("deposit") Deposit deposit, HttpSession session, Model model){
        try {
            System.out.println("sum: "+deposit.getDepositValue());
            UserAcc userAcc = (UserAcc)session.getAttribute("infoUser");
            System.out.println(userAcc.getEmail());
            userService.deposit(userAcc.getEmail(),deposit.getDepositValue());
            session.setAttribute("infoUser", userService.find(userAcc.getEmail()));
            depositService.createDepositCheck(userAcc,deposit.getDepositValue());
            return "redirect:/";
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("exception", e.getMessage());
            return "ErrorPage";
        }

    }
    @GetMapping("/transfer") //гетмап трансфера
    public String transfer(Model model, HttpSession session){
        if(session.getAttribute("infoUser") == null) {
            model.addAttribute("title", "Банк");
            return "bank";
        }
        else{
            UserAcc finalUserAcc = (UserAcc)session.getAttribute("infoUser");
            model.addAttribute("transfer", new Transfer());
            model.addAttribute("email", finalUserAcc.getEmail() );
            model.addAttribute("balance", finalUserAcc.getBalance());
            return "transferPage";
        }
    }
    @PostMapping("/transfer") //постмап трасфера, перевод от 1 пользователя к другому
    public String transferValue(@ModelAttribute("transfer") Transfer transfer, HttpSession session, Model model){
        try {
            UserAcc userAcc = (UserAcc)session.getAttribute("infoUser");
            if(userAcc.getEmail() == transfer.getReceiverEmail()){
                throw new Exception("You tried to transfer money to yourself?");
            }
            if(userAccRepository.existsByEmail(transfer.getReceiverEmail()) == false){
                throw new Exception("No user found");
            }
            else {
                userService.transaction(userAcc.getEmail(), transfer.getReceiverEmail(), transfer.getTransferAmount());
                transfer.setSenderEmail(userAcc.getEmail());
                transferService.createTransferCheck(transfer);
                session.setAttribute("infoUser", userService.find(userAcc.getEmail()));
                return "redirect:/";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("exception", e.getMessage());
            return "ErrorPage";
        }

    }
}
