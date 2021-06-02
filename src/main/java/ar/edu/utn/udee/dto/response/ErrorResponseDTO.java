package ar.edu.utn.udee.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@ApiModel(value = "Error Response", description = "Information of the error ")
public class ErrorResponseDTO {

    @JsonProperty
    @ApiModelProperty(value = "Code HttpStatus Error")
    private Integer code;

    @JsonProperty
    @ApiModelProperty(value = "Name HttpStatus Error")
    private String status;

    @JsonProperty
    @ApiModelProperty(value = "Message of the Error")
    private String message;

    @JsonProperty
    @ApiModelProperty(value = "URI of the Error")
    private String uri;

}