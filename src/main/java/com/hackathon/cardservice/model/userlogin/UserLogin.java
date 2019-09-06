package com.hackathon.cardservice.model.userlogin;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UserLogin {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
