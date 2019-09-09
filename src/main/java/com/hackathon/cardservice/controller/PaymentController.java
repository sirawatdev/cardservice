package com.hackathon.cardservice.controller;

import com.hackathon.cardservice.api.SCBOpenApi;
import com.hackathon.cardservice.model.Payments;
import com.hackathon.cardservice.model.openApiResponse.paymentResponse.PaymentResponseModel;
import com.hackathon.cardservice.model.openApiResponse.tokenResponse.TokenResponseModel;
import com.hackathon.cardservice.model.response.ResponseModel;
import com.hackathon.cardservice.model.response.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path="/payment")
public class PaymentController {
    private SCBOpenApi scbOpenApi;

    @Autowired
    public PaymentController(SCBOpenApi scbOpenApi){
        this.scbOpenApi = scbOpenApi;
    }

    @PostMapping(path = "/pay")
    public HttpEntity<ResponseModel> createPayment(@Valid @RequestBody Payments body) throws Exception {
        StatusModel status = new StatusModel("200", "success");
        PaymentResponseModel payment =  scbOpenApi.getPay(body);
        return ResponseEntity.ok(new ResponseModel(status, payment));
    }

}
