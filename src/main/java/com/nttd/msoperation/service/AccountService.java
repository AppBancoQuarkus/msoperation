package com.nttd.msoperation.service;

import com.nttd.msoperation.dto.AccountDto;
import com.nttd.msoperation.dto.ResponseDto;

public interface AccountService {
    
    public ResponseDto addAccount(AccountDto accountDto);
}
