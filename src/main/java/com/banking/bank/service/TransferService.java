package com.banking.bank.service;

import com.banking.bank.models.Deposit;
import com.banking.bank.models.Transfer;
import com.banking.bank.models.UserAcc;
import com.banking.bank.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;
    public void createTransferCheck(Transfer transfer){
        transferRepository.save(transfer);
    }
}
