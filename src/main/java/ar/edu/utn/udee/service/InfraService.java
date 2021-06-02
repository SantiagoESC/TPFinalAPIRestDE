package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.MeasurementRequestDTO;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import ar.edu.utn.udee.models.ElectricalMeter;
import ar.edu.utn.udee.repository.ElectricalMeasurementRepository;
import ar.edu.utn.udee.repository.ElectricalMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InfraService {

    private final ElectricalMeterRepository electricalMeterRepository;
    private final ElectricalMeasurementRepository electricalMeasurementRepository;

    @Autowired
    public InfraService(ElectricalMeterRepository electricalMeterRepository, ElectricalMeasurementRepository electricalMeasurementRepository) {
        this.electricalMeterRepository = electricalMeterRepository;
        this.electricalMeasurementRepository = electricalMeasurementRepository;
    }

    @Transactional
    public ElectricalMeasurement addMeasurement(MeasurementRequestDTO measurement) throws NotFoundException{

        ElectricalMeter electricalMeter = this.electricalMeterRepository.findBySerialNumber(measurement.getSerialNumber())
                .orElseThrow(()-> new NotFoundException("Electrical Meter Not Found with serial number"
                        + measurement.getSerialNumber()));

        ElectricalMeasurement newMeasurement = ElectricalMeasurement.builder()
                .electricalMeter(electricalMeter)
                .measurementKWH(measurement.getMeasurementKWH())
                .measurementDate(measurement.getDate()).build();

        return this.electricalMeasurementRepository.save(newMeasurement);
    }

}
