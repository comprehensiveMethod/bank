package com.banking.bank.service;

import com.banking.bank.models.Deposit;
import com.banking.bank.models.UserAcc;
import com.banking.bank.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;
    public void createDepositCheck(UserAcc userAcc, Long sum){
        Deposit deposit = new Deposit();
        deposit.setDepositValue(sum);
        deposit.setEmail(userAcc.getEmail());
        System.out.println("Created deposit check №"+deposit.getId()+ " email: "+deposit.getEmail()+" on value: "+deposit.getDepositValue());
        depositRepository.save(deposit);
    }
}
