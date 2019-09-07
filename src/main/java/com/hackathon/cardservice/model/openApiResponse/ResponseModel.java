package com.hackathon.cardservice.model.openApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseModel {

    @JsonProperty("status")
    private StatusModel status;

    @JsonProperty("data")
    private Object data;

}
