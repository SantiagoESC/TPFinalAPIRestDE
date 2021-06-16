package ar.edu.utn.udee.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterClientDTO implements Serializable {

    private static final long serialVersionUID = -5891121614634418282L;

    @Size(min=1, max=16)
    @NotBlank(message = "Firstname is mandatory")
    private String firstName;

    @Size(min=1, max=16)
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;

    @Size(min = 7, max = 9)
    @NotBlank(message = "Document Number is mandatory")
    private String documentNumber;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[\\w\\W]{8,}$")
    private String password;

}
