package com.hackathon.cardservice.constants;

import lombok.Getter;

@Getter
public enum Response {

    SUCCESS_GET("2000","success"), //200
    CREATE_SUCCESS("2001","create success"), //200
    INTERNAL_SERVER_ERROR("5000","internal server error"),
    BAD_REQUEST("4000","bad request"), //400
    WRONG_VARIABLE_TYPE("7777","wrong variable type"), //400
    INPUT_WRONG_OR_MISSING("4005","missing required body"), //400
    CARD_NOTFOUND("4004","card not found"), //400
    WRONG_METHOD("9200","wrong method provided"), //405
    PAYMENT_NOTFOUND("4004","payment not found"), //400
    USER_NOTFOUND("4004","user not found"), //400
    PATH_NOT_FOUND("4444", "path not found"); //404

    private final String code;
    private final String message;

    Response(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
