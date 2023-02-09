package com.nttd.msoperation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private double current_amount;
    private double starting_amount;
    private String description;
    private String flag_account;
    private String startdate;
    private String courtdate;    
    private int quota;
    private String paymentdate;
    private long IdCustomer;
    private long IdBANKCARD;

    public AccountDto() {
    }
    
    
}
