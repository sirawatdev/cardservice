package com.hackathon.cardservice.api;

import com.hackathon.cardservice.model.openApiResponse.paymentResponse.PaymentResponseModel;
import com.hackathon.cardservice.model.openApiResponse.tokenResponse.TokenResponseModel;
import com.hackathon.cardservice.util.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class SCBOpenApi {
    private RestTemplate restTemplate;

    @Value("${spring.openApiService.host}")
    private String host;

    @Autowired
    public SCBOpenApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public TokenResponseModel getToken() throws Exception {
        String data = "{}";
        JSONObject keyBody = new JSONObject();
        keyBody.put("applicationKey", "l7d1c5e7b0d8f54828841690126a303d58");
        keyBody.put("applicationSecret", "57a4c9f0a0ad4d7ab0c0373b9b382772");
        try{

            RequestEntity requestEntity = RequestEntity.post(URI.create(host + "/v1/oauth/token"))
                .header("resourceOwnerId", "l7d1c5e7b0d8f54828841690126a303d58")
                .header("requestUId", UUID.randomUUID().toString())
                .header("accept-language", "EN")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(keyBody.toString());
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            System.err.println(responseEntity);
            if(responseEntity.getStatusCode().value() == 200){
                System.err.println(Util.readValue(data, TokenResponseModel.class));
                return Util.readValue(data, TokenResponseModel.class);
            }
        }
        catch (Exception exception){
            System.err.println("exception: " + exception);
            throw exception;
        }
        return Util.readValue(data, TokenResponseModel.class);
    }

//    public PaymentResponseModel getPayment() throws Exception {
//        String data = "{}";
//        return Util.readValue(data, PaymentResponseModel.class);
//    }

}
