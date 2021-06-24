package ar.edu.utn.udee.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class MeasurementRequestDTO implements Serializable {

    private static final long serialVersionUID = 4154266404377302272L;

    @NotNull
    private String serialNumber;
    @NotNull
    private Double measurementKWH;
    @NotNull
    private LocalDateTime date;

}
