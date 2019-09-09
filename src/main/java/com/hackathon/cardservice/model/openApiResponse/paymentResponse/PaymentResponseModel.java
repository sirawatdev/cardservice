package com.hackathon.cardservice.model.openApiResponse.paymentResponse;

import lombok.Data;

@Data
public class PaymentResponseModel {
    private String transactionId;
    private String deeplinkUrl;
    private String userRefId;
}
