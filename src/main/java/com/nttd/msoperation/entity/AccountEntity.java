package com.nttd.msoperation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "BQMACCOUNTCUSTOMER")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long IdAccountCustomer;
    @Column
    private String flag_creation;
    @Column
    private double current_amount;
    @Column
    private double starting_amount;
    @Column
    private String description;
    @Column
    private String flag_account;
    @Column
    private String startdate;
    @Column
    private String courtdate;    
    @Column
    private int quota;
    @Column
    private String paymentdate;
    
    @Column
    private String state;

    @Column
    private long IdCustomer;
    @Column
    private long IdBANKCARD;
    
}
