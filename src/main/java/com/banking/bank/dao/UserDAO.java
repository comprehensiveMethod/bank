package com.banking.bank.dao;

import com.banking.bank.models.UserAcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Transactional
@Repository
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public UserAcc findAccount(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(UserAcc.class, email);
    }

}
}
