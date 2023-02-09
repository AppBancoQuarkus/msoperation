package com.nttd.msoperation.service.impl;

import com.nttd.msoperation.dto.AccountDto;
import com.nttd.msoperation.dto.ResponseDto;
import com.nttd.msoperation.service.AccountService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {
    
  

    @Override
    @Transactional
    public ResponseDto addAccount(AccountDto accountDto){
        try{
            return  new ResponseDto(201,"Exitoso.");
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        }
            
    }

}
