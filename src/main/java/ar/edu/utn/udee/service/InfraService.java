package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.MeasurementRequestDTO;
import ar.edu.utn.udee.models.ElectricalMeasurement;

public interface InfraService {

    ElectricalMeasurement addMeasurement(MeasurementRequestDTO measurement);

}
