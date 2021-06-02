package ar.edu.utn.udee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO implements Serializable {

    @Size(min = 7, max = 9)
    @NotBlank(message = "ID Number is mandatory")
    private String idNumber;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[\\w\\W]{8,}$")
    private String password;

}