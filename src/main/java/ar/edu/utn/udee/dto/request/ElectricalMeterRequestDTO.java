package ar.edu.utn.udee.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
public class ElectricalMeterRequestDTO implements Serializable {

    private static final long serialVersionUID = -3028598394395472657L;

    @Size(min = 7, max = 9)
    @NotBlank(message = "Ducument Number is mandatory")
    private String documentNumber;

    @Size(min=1, max=32)
    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @Size(min=1, max=32)
    @NotBlank(message = "Model is mandatory")
    private String model;

    @Size(min=1, max=32)
    @NotBlank(message = "Street name is mandatory")
    private String streetName;

    @Size(min = 1, max = 6)
    @NotBlank(message = "Street number is mandatory")
    private Integer streetNumber;

    @Size(min = 1, max = 2)
    private String floor;

    @Size(min = 1, max = 3)
    private String department;

    @Size(min = 2, max = 6)
    @NotBlank(message = "Zipcode number is mandatory")
    private String zipCode;

    @Size(min=1, max=32)
    @NotBlank(message = "City name is mandatory")
    private String city;
}
