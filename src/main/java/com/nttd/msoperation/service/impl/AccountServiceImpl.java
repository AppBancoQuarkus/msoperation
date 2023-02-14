package com.nttd.msoperation.service.impl;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.nttd.msoperation.api.BankCardApi;
import com.nttd.msoperation.api.CustomerApi;
import com.nttd.msoperation.api.response.BankCardResponse;
import com.nttd.msoperation.api.response.CustomerResponse;
import com.nttd.msoperation.dto.AccountDto;
import com.nttd.msoperation.dto.ResponseDto;
import com.nttd.msoperation.entity.AccountEntity;
import com.nttd.msoperation.repository.AccountRepository;
import com.nttd.msoperation.service.AccountService;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {
    
    @Inject
    AccountRepository accountRepository;

    @RestClient
    BankCardApi bankCardApi;

    @RestClient
    CustomerApi customerApi;

    @Override
    public ResponseDto getAllAccount() {
        try{
            List<AccountEntity> listaccount = accountRepository.listAll(Sort.by("IdCustomer"));
            if(listaccount.size() == 0)
                return  new ResponseDto(204,"Account not found.","");
            else return  new ResponseDto(200,"Exitoso.",listaccount);
            
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        } 
    }

    @Override
    public ResponseDto getByIdAccount(long id) {
       
        try{
            AccountEntity accountEntity = accountRepository.findById(id);
            if(accountEntity == null)
                return  new ResponseDto(204,"Account not found.","");
            else return  new ResponseDto(200,"Exitoso.",accountEntity);
            
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        } 

    }

    @Override
    @Transactional
    public ResponseDto addAccount(AccountDto accountDto){
        try{            
            AccountEntity acEntity = new AccountEntity();            
            CustomerResponse customerResponse = customerApi.addCustomer(accountDto.getCustomerRequest());
            if(customerResponse.getCode() == Response.Status.CREATED.getStatusCode())
                acEntity.setIdCustomer(customerResponse.getCustomer().getIdCustomer());
            else return  new ResponseDto(400,"Bad Request.","Error en registrar el cliente.");

            acEntity.setFlag_creation(accountDto.getFlag_creation());
            acEntity.setDescription(accountDto.getDescription());            
            acEntity.setState("A");

            // tipo de registro en la cuenta TC:TARJETA DE CREDITO
            // C: CREDITO - TD: TARJETA DE DEBITO
            if(acEntity.getFlag_creation().equals("TC")){
                BankCardResponse bankCardResponse = bankCardApi.addBankCard(accountDto.getBankCardRequest());
                if(bankCardResponse.getCode() == Response.Status.CREATED.getStatusCode())
                    acEntity.setIdBANKCARD(bankCardResponse.getBankCardObject().getIdBANKCARD());
                else return  new ResponseDto(400,"Bad Request.","Error en registrar la tarjeta.");
                acEntity.setCurrent_amount(accountDto.getCurrent_amount());
                acEntity.setStarting_amount(accountDto.getStarting_amount());
                acEntity.setPaymentdate(accountDto.getPaymentdate());
                acEntity.setCourtdate(accountDto.getCourtdate());

            }else if(acEntity.getFlag_creation().equals("C")){
                acEntity.setCurrent_amount(accountDto.getCurrent_amount());
                acEntity.setQuota(accountDto.getQuota());
                acEntity.setStarting_amount(accountDto.getStarting_amount());
                acEntity.setStartdate(accountDto.getStartdate());
                acEntity.setPaymentdate(accountDto.getPaymentdate());

            }else if(acEntity.getFlag_creation().equals("TD")){
                acEntity.setFlag_account(accountDto.getFlag_account());
                BankCardResponse bankCardResponse = bankCardApi.addBankCard(accountDto.getBankCardRequest());
                if(bankCardResponse.getCode() == Response.Status.CREATED.getStatusCode())
                    acEntity.setIdBANKCARD(bankCardResponse.getBankCardObject().getIdBANKCARD());
                else return  new ResponseDto(400,"Bad Request.","Error en registrar la tarjeta.");
            }

            accountRepository.persist(acEntity);
            return  new ResponseDto(201,"Exitoso.",acEntity);
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        }
            
    }





    @Override
    @Transactional
    public ResponseDto updateAccount(long id, AccountDto accountDto) {
        try{
            AccountEntity accountEntity = accountRepository.findById(id);
            if(accountEntity == null)
                return  new ResponseDto(204,"Account not found.","");
            else{
                if(!accountDto.getFlag_account().equals(""))
                    accountEntity.setFlag_account(accountDto.getFlag_account());      

                // considerando que no disminucion de credito solo aumentos    
                accountEntity.setStarting_amount(accountEntity.getStarting_amount()+accountDto.getStarting_amount());
                if(!accountDto.getCourtdate().equals(""))    
                    accountEntity.setCourtdate(accountDto.getCourtdate());
                  
                accountEntity.setCurrent_amount(accountEntity.getCurrent_amount()+accountDto.getCurrent_amount());
                accountRepository.persist(accountEntity);
                return  new ResponseDto(200,"Exitoso.",accountEntity);
            } 
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        } 
    }

    @Override
    @Transactional
    public ResponseDto deleteAccount(long id) {
        try{
            AccountEntity accountEntity = accountRepository.findById(id);
            if(accountEntity == null)
                return  new ResponseDto(204,"Account not found.","");
            else{
                accountEntity.setState("I");
                accountRepository.persist(accountEntity);
                return  new ResponseDto(200,"Exitoso.",accountEntity);
            } 
        }catch(Exception ex){
            return  new ResponseDto(400,"Bad Request.",ex.getMessage());
        } 
    }

}
