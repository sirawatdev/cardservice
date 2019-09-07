package com.hackathon.cardservice.model.openApiResponse.tokenResponse;

import lombok.Data;

@Data
public class TokenResponseModel {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private Long expiresAt;
}
