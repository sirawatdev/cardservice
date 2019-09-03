package com.hackathon.cardservice.exception;

import com.hackathon.cardservice.constants.Response;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CardException extends Exception{
    private Response response;
    private HttpStatus httpStatus;
    public CardException(String message){
        super(message);
    }

    public CardException(String message, Throwable cause){
        super(message,cause);
    }

    public CardException(Response response, HttpStatus httpStatus){
        this.response = response;
        this.httpStatus = httpStatus;
    }
}
