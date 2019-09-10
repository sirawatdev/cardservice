package com.hackathon.cardservice.api;

import com.hackathon.cardservice.model.Payments;
import com.hackathon.cardservice.model.openApiResponse.paymentResponse.PaymentResponseModel;
import com.hackathon.cardservice.model.openApiResponse.tokenResponse.TokenResponseModel;
import com.hackathon.cardservice.util.Util;
import org.json.JSONArray;
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
            if(responseEntity.getStatusCode().value() == 200){
                JSONObject resp = new JSONObject(responseEntity.getBody());
                data = resp.get("data").toString();
                System.err.println(data);
                return Util.readValue(data, TokenResponseModel.class);
            }
        }
        catch (Exception exception){
            System.err.println("exception: " + exception);
            throw exception;
        }
        return Util.readValue(data, TokenResponseModel.class);
    }

    public PaymentResponseModel getPay(Payments body) throws Exception {
        String data = "{}";
        String accessToken = this.getToken().getAccessToken();
        // sub sub body
        JSONArray info = new JSONArray();
        JSONObject subInfo = new JSONObject();
        subInfo.put("type", "payment");
        subInfo.put("title", "prepaid to card");
        subInfo.put("header", "ECard+");
        subInfo.put("description", "payment for prepaid card via ECard+");
        subInfo.put("imageUrl", "");
        info.put(subInfo);
        //sub sub body
        JSONObject analytics = new JSONObject();
        analytics.put("Product category", "Prepaid Card");
        analytics.put("Partner", "ECard+");
        analytics.put("Product code", "1029384756");
        analytics.put("Detail1", "payment for ECard+");
        analytics.put("Detail2", "from card number " + body.getCardId());
        analytics.put("Detail3", "from user id " +  body.getUserId());
        analytics.put("Detail4", "payment process at " + body.getDateTime());
        analytics.put("Detail5", "");
        analytics.put("Detail6", "");
        // sub body
        JSONObject meta = new JSONObject();
        meta.put("paymentInfo", info);
        meta.put("analytics", analytics);
        // body
        JSONObject payBody = new JSONObject();
        payBody.put("paymentAmount", body.getAmount());
        payBody.put("transactionType", "PAYMENT");
        payBody.put("transactionSubType", "BPA");
        payBody.put("ref1", "2003002913251522");
        payBody.put("ref2", "SCB");
        payBody.put("accountTo", "267701494471979");
        payBody.put("merchantMetaData", meta);

        // request to api

        try{
            RequestEntity requestEntity = RequestEntity.post(URI.create(host + "/v2/deeplink/transactions"))
                    .header("authorization", "Bearer " + accessToken)
                    .header("resourceOwnerId", "l7d1c5e7b0d8f54828841690126a303d58")
                    .header("requestUId", UUID.randomUUID().toString())
                    .header("channel", "scbeasy")
                    .header("accept-language", "EN")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(payBody.toString());
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            if(responseEntity.getStatusCode().value() == 201){
                JSONObject resp = new JSONObject(responseEntity.getBody());
                data = resp.get("data").toString();
                System.err.println(data);
                return Util.readValue(data, PaymentResponseModel.class);
            }
        }
        catch (Exception exception){
            System.err.println("exception: " + exception);
            throw exception;
        }
        return Util.readValue(data, PaymentResponseModel.class);
    }


}
