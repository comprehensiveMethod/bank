package com.banking.bank.repository;

import com.banking.bank.models.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {
}
