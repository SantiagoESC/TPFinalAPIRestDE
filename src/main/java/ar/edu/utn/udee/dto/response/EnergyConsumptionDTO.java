package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnergyConsumptionDTO {

    private UserResponseDTO user;

    private Double measurementTotalKWH;
    private Double totalAmount;
}
