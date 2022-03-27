package com.banking.bank.repo;

import com.banking.bank.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<User, Long> {

}
