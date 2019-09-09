package com.hackathon.cardservice.model.userlogin;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotNull;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class UserLogin {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
