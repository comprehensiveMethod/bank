package com.banking.bank;
import static org.assertj.core.api.Assertions.assertThat;

import com.banking.bank.models.UserAcc;
import com.banking.bank.repository.UserAccRepository;
import com.banking.bank.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepositoryTest {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    UserAccRepository userAccRepository;
    @Test
    void test(){
        UserAcc userAcc = userDetailsService.find("timur32240@gmail.com");
        System.out.println(userAcc.getId());
        System.out.println(userAcc.getEmail());
        System.out.println(userAcc.getBalance());
    }
}
