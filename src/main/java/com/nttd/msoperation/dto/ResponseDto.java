package com.nttd.msoperation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseDto {
    
    private int code;
    private String message;
    private String errorMessage;
    private String description;


    
    public ResponseDto() {
    }



    public ResponseDto(int code,String message) {
        this.code = code;
        this.message = message;
    }



    public ResponseDto(int code, String errorMessage, String description) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.description = description;
    }


    

}
