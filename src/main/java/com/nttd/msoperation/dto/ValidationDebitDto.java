package com.nttd.msoperation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDebitDto {

    private long IdAccountCustomer;
    private boolean respuesta;
    private String flag_creation;
    private double current_amount;
    private long IdBANKCARD;
    private String state;

}
