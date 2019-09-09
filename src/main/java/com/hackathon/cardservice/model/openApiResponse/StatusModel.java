package com.hackathon.cardservice.model.openApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusModel {
    @JsonProperty("code")
    private Long code;

    @JsonProperty("description")
    private String description;

    public StatusModel() { }

    public StatusModel(Long code, String description) {
        this.code = code;
        this.description = description;
    }

}
