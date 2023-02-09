package com.nttd.msoperation.service.impl;

import com.nttd.msoperation.dto.AccountDto;
import com.nttd.msoperation.dto.ResponseDto;
import com.nttd.msoperation.entity.AccountEntity;
import com.nttd.msoperation.repository.AccountRepository;
import com.nttd.msoperation.service.AccountService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {
    
    @Inject
    AccountRepository accountRepository;

    @Override
    @Transactional
    public ResponseDto addAccount(AccountDto accountDto){
        try{
            AccountEntity acEntity = new AccountEntity();
            acEntity.setCourtdate(accountDto.getCourtdate());            
            accountRepository.persist(acEntity);
            return  new ResponseDto(201,"Exitoso.");
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        }
            
    }

}
