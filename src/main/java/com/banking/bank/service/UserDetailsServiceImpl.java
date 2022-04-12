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

        try {
            UserAcc trueUser = userAccRepository.findByEmail(email).get();
            System.out.println("deposit: "+trueUser.getId()+" "+ trueUser.getEmail()+" "+trueUser.getBalance()+" for sum: "+sum);
            Long startSum = trueUser.getBalance();
            trueUser.setBalance(startSum+sum);
            userAccRepository.save(trueUser);
        }catch (Exception e) {
            return;
        }

    }
    public boolean checkUser(UserAcc userAcc){
        if(userAccRepository.existsByEmailAndPassword(userAcc.getEmail(),userAcc.getPassword()) == true) return true;
        else return false;
    }
    public UserAcc find(String email){
        Optional<UserAcc> userAcc1 = userAccRepository.findByEmail(email);
        System.out.println("find: "+userAcc1.get().getId()+" "+ userAcc1.get().getEmail()+" "+userAcc1.get().getBalance());
        return userAcc1.get();
    }
    public void transaction(String email1,String email2, Long sum){
        try {
            UserAcc transactionHolder = userAccRepository.findByEmail(email1).get();
            System.out.println("Transaction holder: "+ transactionHolder.getEmail());
            UserAcc transactionGetter = userAccRepository.findByEmail(email2).get();
            System.out.println("Transaction getter: "+ transactionGetter.getEmail());
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
