package com.banking.bank.service;

import com.banking.bank.models.UserAcc;

import com.banking.bank.repository.UserAccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl {
    @Autowired
    UserAccRepository userAccRepository;

    public void saveUser(String email, String pass){
        UserAcc userAcc = null;
        userAcc.setBalance(0L);
        userAcc.setEmail(email);
        userAcc.setPassword(pass);
    }
    public void deposit(String email, Long sum){
        Optional<UserAcc> userAcc = userAccRepository.findByEmail(email);
        try {
            UserAcc trueUser = userAcc.get();
            trueUser.setBalance(trueUser.getBalance()+sum);
            userAccRepository.save(trueUser);
        }catch (Exception e) {return;}

    }
    public void transaction(String email1,String email2, Long sum){
        Optional<UserAcc> userAcc1 = userAccRepository.findByEmail(email1);
        Optional<UserAcc> userAcc2 = userAccRepository.findByEmail(email1);
        try {
            UserAcc transactionHolder = userAcc1.get();
            UserAcc transactionGetter = userAcc2.get();
            if(transactionHolder.getBalance()<sum) {
                throw new Exception("Not enough money to transaction from "+transactionHolder.getEmail()+" to " + transactionGetter.getEmail());
            }else{
            transactionHolder.setBalance(transactionHolder.getBalance()-sum);
            transactionGetter.setBalance(transactionGetter.getBalance()+sum);
            userAccRepository.save(transactionHolder);
            userAccRepository.save(transactionGetter);
            }
        }catch (Exception e) {return;}

    }

}
