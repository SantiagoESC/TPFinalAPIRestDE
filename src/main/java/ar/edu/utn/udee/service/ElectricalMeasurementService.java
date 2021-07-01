package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.response.EnergyConsumptionDTO;
import ar.edu.utn.udee.dto.response.MeasurementsDTO;

import java.time.LocalDateTime;

public interface ElectricalMeasurementService {

    MeasurementsDTO getElectricalMeasurements(String documentNumber, LocalDateTime from, LocalDateTime to);
    EnergyConsumptionDTO getEnergyConsumption(String documentNumber, LocalDateTime from, LocalDateTime to);
}
