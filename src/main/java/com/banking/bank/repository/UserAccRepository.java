package com.banking.bank.repository;

import com.banking.bank.models.UserAcc;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccRepository extends CrudRepository<UserAcc,Long> {
    Optional<UserAcc> findByEmail(String email);
}
