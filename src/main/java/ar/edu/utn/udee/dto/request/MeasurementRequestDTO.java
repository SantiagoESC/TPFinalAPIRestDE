package ar.edu.utn.udee.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class MeasurementRequestDTO {

    @NotNull
    private String serialNumber;
    @NotNull
    private Double measurementKWH;
    @NotNull
    private LocalDateTime date;

}
