package com.hackathon.cardservice.controller;

import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.exception.CardException;
import com.hackathon.cardservice.model.Users;
import com.hackathon.cardservice.model.response.ResponseModel;
import com.hackathon.cardservice.model.response.StatusModel;
import com.hackathon.cardservice.model.userlogin.UserLogin;
import com.hackathon.cardservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path="/user")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class.getName());
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = "/verifyuser")
    public HttpEntity<ResponseModel> verifyUser(@Valid @RequestBody UserLogin userLogin) throws CardException {

        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

        boolean userLoginSuccess = userService.verifyUser(userLogin);

        String resp="ok";
        return ResponseEntity.ok(new ResponseModel(status, resp));
    }
    @PostMapping(path ="/register")
    public HttpEntity<ResponseModel> verifyUser(@Valid @RequestBody Users user){
        //only create user into database for first user
        StatusModel status = new StatusModel(Response.SUCCESS_GET.getCode(),
                Response.SUCCESS_GET.getMessage());

        userService.regisUSer(user);

        String resp="ok";
        return ResponseEntity.ok(new ResponseModel(status, resp));
    }

}
