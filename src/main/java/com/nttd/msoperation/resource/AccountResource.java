package com.nttd.msoperation.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.nttd.msoperation.dto.AccountDto;
import com.nttd.msoperation.dto.ResponseDto;
import com.nttd.msoperation.service.AccountService;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;

@Path("/api/account")
public class AccountResource {

    @Inject
    AccountService accountService;

    @Inject
    Logger logger;

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(AccountDto accountDto) {
        logger.info("Iniciando el metodo de registrar cuenta - Resource.");
        ResponseDto responsedto = accountService.addAccount(accountDto);
        return Response.ok(responsedto).status(responsedto.getCode()).build();
    }



}
