package com.banking.bank.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "depositchecks")
public class Deposit {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Column(length = 50)
    @Getter
    @Setter
    private String email;
    @Column(length = 12, name = "depositvalue")
    @Getter
    @Setter
    private Long depositValue;
}
