package com.banking.bank.repo;

import com.banking.bank.models.UserAcc;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<UserAcc, Long> {

}
