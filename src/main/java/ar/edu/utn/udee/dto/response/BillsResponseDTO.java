package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BillsResponseDTO {

    private String fullName;
    private String documentNumber;

    private String electricalMeter;

    private String address;

    private String rateType;
    private Double ratePrice;

    private LocalDate measurementDateInitial;
    private Double measurementInitial;

    private LocalDate measurementDateFinal;
    private Double measurementFinal;

    private LocalDate expirationDate;

    private Double measurementTotalKWH;
    private Double totalAmount;

}
