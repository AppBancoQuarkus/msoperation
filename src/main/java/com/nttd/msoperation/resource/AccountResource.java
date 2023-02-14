package com.nttd.msoperation.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
//import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

import com.nttd.msoperation.dto.AccountDto;
import com.nttd.msoperation.dto.ResponseDto;
import com.nttd.msoperation.service.AccountService;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;

@Path("/api/account")
public class AccountResource {

    @Inject
    AccountService accountService;

    @Inject
    Logger logger;
/* Obterner todas las cuentas del cliente */

    @GET
    @Retry(maxRetries = 3)
    public Response getAllAccount(){
        logger.info("Iniciando el metodo de obtener todas las cuentas - Resource.");
        ResponseDto responsedto = accountService.getAllAccount();
        return Response.ok(responsedto).status(responsedto.getCode()).build();

    }

    /* Obterner los datos de una cuenta del cliente */
    @GET
    @Path("{id}")
    @Timeout(900)
    public Response getByIdAccount(@PathParam("id") long id){
        logger.info("Iniciando el metodo de obtener una cuenta - Resource.");
        ResponseDto responsedto = accountService.getByIdAccount(id);
        return Response.ok(responsedto).status(responsedto.getCode()).build();

    }

    /* Registrar una cuenta del cliente */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(AccountDto accountDto) {
        logger.info("Iniciando el metodo de registrar cuenta - Resource.");
        ResponseDto responsedto = accountService.addAccount(accountDto);
        return Response.ok(responsedto).status(responsedto.getCode()).build();
    }

/* Actualizar el monto credito o el tiempo de pago y corto de la cuenta del cliente */
    @PUT
    @Path("{id}")
    public Response updateAccount(@PathParam("id") long id,AccountDto accountDto){
        logger.info("Iniciando el metodo de actualizar una cuenta - Resource.");
        ResponseDto responsedto = accountService.updateAccount(id,accountDto);
        return Response.ok(responsedto).status(responsedto.getCode()).build();

    }

    /* Cambiar de estado la cuenta del cliente A:Activo o I:Inactivo  */
    @DELETE
    @Path("{id}")
    public Response deleteAccount(@PathParam("id") long id){
        logger.info("Iniciando el metodo de eliminar una cuenta - Resource.");
        ResponseDto responsedto = accountService.deleteAccount(id);
        return Response.ok(responsedto).status(responsedto.getCode()).build();

    }


}
