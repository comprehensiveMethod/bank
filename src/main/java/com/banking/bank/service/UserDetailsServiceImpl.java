package com.banking.bank.service;

import com.banking.bank.models.UserAcc;

import com.banking.bank.repository.UserAccRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl {
    @Autowired
    UserAccRepository userAccRepository;

    public void createUser(UserAcc userAcc){
        userAcc.setBalance(100L);
        userAccRepository.save(userAcc);
    }
    public void deposit(String email, Long sum){
        Optional<UserAcc> userAcc = userAccRepository.findByEmail(email);
        try {
            UserAcc trueUser = userAcc.get();
            trueUser.setBalance(trueUser.getBalance()+sum);
            userAccRepository.save(trueUser);
        }catch (Exception e) {
            return;
        }

    }
    public boolean checkUser(UserAcc userAcc){
        if(userAccRepository.existsByEmailAndPassword(userAcc.getEmail(),userAcc.getPassword()) == true) return true;
        else return false;
    }
    public UserAcc find(UserAcc userAcc){
        Optional<UserAcc> userAcc1 = userAccRepository.findByEmail(userAcc.getEmail());
        return userAcc1.get();
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
