package ar.edu.utn.udee.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = -2005337807991914802L;

    @Size(min=1, max=32)
    private String streetName;

    @Size(min = 1, max = 5)
    private Integer streetNumber;

    @Size(min = 1, max = 2)
    private String floor;

    @Size(min = 1, max = 3)
    private String department;

    @Size(min = 2, max = 6)
    private String zipCode;

    @Size(min=1, max=32)
    private String city;
}
