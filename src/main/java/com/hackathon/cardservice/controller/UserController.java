package com.hackathon.cardservice.controller;

import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.model.Cards;
import com.hackathon.cardservice.model.response.ResponseModel;
import com.hackathon.cardservice.model.response.StatusModel;
import com.hackathon.cardservice.model.userlogin.UserLogin;
import com.hackathon.cardservice.service.CardService;
import com.hackathon.cardservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class UserController {

    private static final Logger log = LogManager.getLogger(CardController.class.getName());
    private UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;
    }

    @PostMapping
    public HttpEntity<ResponseModel> verifyUser(@Valid @RequestBody UserLogin userLogin){

        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

        boolean userLoginSuccess = userService.verifyUser(userLogin);

        String resp="ok";
        return ResponseEntity.ok(new ResponseModel(status, resp));
    }

}
