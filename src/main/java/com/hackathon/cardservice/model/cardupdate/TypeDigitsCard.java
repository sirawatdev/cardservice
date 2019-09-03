package com.hackathon.cardservice.model.cardupdate;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TypeDigitsCard {


    @Id
    private Long id;

    @NotNull
    private Integer type;

    @NotNull
    private Long digits;
}
