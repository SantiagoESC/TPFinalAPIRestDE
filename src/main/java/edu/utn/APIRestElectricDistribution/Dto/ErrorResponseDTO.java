package edu.utn.APIRestElectricDistribution.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDTO {

    @JsonProperty
    private Integer code;

    @JsonProperty
    private String description;

    public ErrorResponseDTO(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
