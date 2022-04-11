package com.banking.bank.repository;

import com.banking.bank.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

}
