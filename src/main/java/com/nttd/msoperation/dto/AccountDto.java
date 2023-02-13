package com.nttd.msoperation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.nttd.msoperation.api.request.BankCardRequest;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {

    private String flag_creation;
    private double current_amount;
    private double starting_amount;
    private String description;
    private String flag_account;
    private String startdate;
    private String courtdate;    
    private int quota;
    private String paymentdate;
    private long IdCustomer;
    private BankCardRequest bankCardRequest;
    private String state;

    public AccountDto() {
    }
    
    
}
