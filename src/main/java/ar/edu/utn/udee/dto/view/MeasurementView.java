package ar.edu.utn.udee.dto.view;

import java.time.LocalDateTime;

public interface MeasurementView {

    String getDocumentNumber();
    String getFullName();

    String getElectricalMeter();
    String getAddress();

    LocalDateTime getMeasurementDate();
    Double getMeasurement();


}
