package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeasurementsDTO {

    private UserResponseDTO client;
    private List<MeasurementDTO> measurements;
}
