package ar.edu.utn.udee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementRequestDTO implements Serializable {

    private static final long serialVersionUID = 4154266404377302272L;

    @NotNull
    private String serialNumber;
    @NotNull
    private Double measurementKWH;
    @NotNull
    private LocalDateTime date;

}
