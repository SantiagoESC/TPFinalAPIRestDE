package ar.edu.utn.udee.dto.view;

import java.time.LocalDateTime;

public interface EnergyConsumptionView {

    String getDocumentNumber();
    String getFullName();

    String getElectricalMeter();
    String getAddress();

    LocalDateTime getInitialDate();
    LocalDateTime getFinalDate();

    Double getTotalEnergyConsumption();
    Double getTotalAmount();

}
