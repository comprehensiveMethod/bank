package com.banking.bank.repository;

import com.banking.bank.models.UserAcc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserAccRepository extends CrudRepository<UserAcc,Long> {
    Optional<UserAcc> findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String pass);
}
