package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ElectricalMeterResponseDTO implements Serializable {

    private String electricalMeter;
    private String address;

}
