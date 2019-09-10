package com.hackathon.cardservice.model.cardupdate;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AmountCard {

    @Transient
    @Id
    private Long id;

    @NotNull
    private Long amount;

    private String refCode;
}
