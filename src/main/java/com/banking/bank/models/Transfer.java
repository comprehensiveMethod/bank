package com.banking.bank.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transferchecks")
public class Transfer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Column(length = 10, name = "transferamount")
    @Getter
    @Setter
    private Long transferAmount;
    @Column(length = 50, name = "senderemail")
    @Getter
    @Setter
    private String senderEmail;
    @Column(length = 50, name = "receiveremail")
    @Getter
    @Setter
    private String receiverEmail;
}
