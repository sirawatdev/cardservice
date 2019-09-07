package com.hackathon.cardservice.controller;

import com.hackathon.cardservice.api.SCBOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path="/payment")
public class PaymentController {
    private SCBOpenApi scbOpenApi;

    @Autowired
    public PaymentController(SCBOpenApi scbOpenApi){
        this.scbOpenApi = scbOpenApi;
    }

    @GetMapping(path = "/token")
    public void getToken() throws Exception {
        scbOpenApi.getToken();
    }

}
