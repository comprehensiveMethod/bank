package com.banking.bank.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class UserAcc {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Column(unique = true, length = 50)
    @Getter
    @Setter
    private String email;
    @Column(length = 64)
    @Getter
    @Setter
    private String password;
    @Column
    @Getter
    @Setter
    private Long balance;

}
