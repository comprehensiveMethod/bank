package com.banking.bank.service;

import com.banking.bank.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
}
