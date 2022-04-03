package com.banking.bank;
import static org.assertj.core.api.Assertions.assertThat;

import com.banking.bank.models.UserAcc;
import com.banking.bank.repo.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void createUser(){
        UserAcc userAcc = new UserAcc();
        userAcc.setEmail("smth@Example.com");
        userAcc.setPassword("123456");
        userAcc.setBalance(0L);

        UserAcc savedUserAcc =clientRepository.save(userAcc);

        UserAcc existUserAcc = testEntityManager.find(UserAcc.class, savedUserAcc.getId());

        assertThat(existUserAcc.getEmail()).isEqualTo(userAcc.getEmail());
    }
}
