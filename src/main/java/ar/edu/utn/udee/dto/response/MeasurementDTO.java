package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasurementDTO {

    private String measurementDate;
    private Double measurement;

}
