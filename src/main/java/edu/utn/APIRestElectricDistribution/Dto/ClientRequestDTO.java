package edu.utn.APIRestElectricDistribution.Dto;

import edu.utn.APIRestElectricDistribution.Domain.Enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequestDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String IDCardNumber;
    private String password;


    public Boolean isValid() {
        return /*cityId != null &&*/ !StringUtils.isBlank(firstName) && !StringUtils.isBlank(lastName) && !StringUtils.isBlank(IDCardNumber)  && !StringUtils.isBlank(password) ;
    }
}
