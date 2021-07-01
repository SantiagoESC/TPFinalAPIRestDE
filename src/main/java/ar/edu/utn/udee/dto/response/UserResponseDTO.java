package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = -527975342683294048L;

    private String fullName;
    private String documentNumber;

    private List<ElectricalMeterResponseDTO> electricalMeters;
}
