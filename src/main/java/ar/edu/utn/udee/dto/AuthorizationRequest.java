package ar.edu.utn.udee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("user")
    private String documentNumber;

    private String password;

}