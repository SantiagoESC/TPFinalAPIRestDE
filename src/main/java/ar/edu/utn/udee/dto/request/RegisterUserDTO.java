package ar.edu.utn.udee.dto.request;

import ar.edu.utn.udee.models.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserDTO implements Serializable {

    private static final long serialVersionUID = -5891121614634418282L;

    @Size(min=1, max=16)
    @NotBlank(message = "Firstname is mandatory")
    private String firstName;

    @Size(min=1, max=16)
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;

    @Size(min = 7, max = 9)
    @NotBlank(message = "ID Number is mandatory")
    private String idNumber;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[\\w\\W]{8,}$")
    private String password;

    @NotNull
    private AddressDTO address;

    private Role role;

}
