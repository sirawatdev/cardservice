package com.hackathon.cardservice.exception;


import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.model.response.ResponseModel;
import com.hackathon.cardservice.model.response.StatusModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class CardExceptionHandler {
    @ExceptionHandler(value= {CardException.class})
    public ResponseEntity<?> handleApiRequestException_BAD_REQUEST(CardException e){
        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(e.getResponse().getCode(),e.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(HttpServletResponse response) throws IOException {
        CardException appointmentsEx =new CardException(Response.WRONG_VARIABLE_TYPE, HttpStatus.BAD_REQUEST);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(appointmentsEx.getResponse().getCode(),appointmentsEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {

        CardException appointmentsEx =new CardException(Response.BAD_REQUEST, HttpStatus.BAD_REQUEST);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(appointmentsEx.getResponse().getCode(),appointmentsEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        CardException appointmentsEx =new CardException(Response.WRONG_VARIABLE_TYPE, HttpStatus.BAD_REQUEST);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(appointmentsEx.getResponse().getCode(),appointmentsEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e){

        CardException appointmentsEx =new CardException(Response.INPUT_WRONG_OR_MISSING, HttpStatus.BAD_REQUEST);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(appointmentsEx.getResponse().getCode(),appointmentsEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNoBody(HttpMessageNotReadableException e){
        CardException appointmentsEx =new CardException(Response.INPUT_WRONG_OR_MISSING, HttpStatus.BAD_REQUEST);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(appointmentsEx.getResponse().getCode(),appointmentsEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> handleUserServiceApi(HttpServerErrorException ex) {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR ;

        CardException vacationEx = new CardException(Response.INTERNAL_SERVER_ERROR, httpStatus);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(vacationEx.getResponse().getCode(),vacationEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleNotFound(HttpClientErrorException ex) {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR ;

        CardException vacationEx = new CardException(Response.INTERNAL_SERVER_ERROR, httpStatus);

        ResponseModel reasponModel = new ResponseModel(
                new StatusModel(vacationEx.getResponse().getCode(),vacationEx.getResponse().getMessage())
        );
        return new ResponseEntity(reasponModel, httpStatus);
    }
}
