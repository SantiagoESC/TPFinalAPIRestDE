package edu.utn.APIRestElectricDistribution.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {

    private String IDCardNumber;
    private String password;
}
