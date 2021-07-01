package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;
import ar.edu.utn.udee.dto.response.EnergyConsumptionDTO;
import ar.edu.utn.udee.dto.response.MeasurementDTO;
import ar.edu.utn.udee.dto.response.MeasurementsDTO;
import ar.edu.utn.udee.dto.response.UserResponseDTO;
import ar.edu.utn.udee.dto.view.EnergyConsumptionView;
import ar.edu.utn.udee.dto.view.MeasurementView;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.repository.ElectricalMeasurementRepository;
import ar.edu.utn.udee.service.ElectricalMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ElectricalMeasurementServiceImpl implements ElectricalMeasurementService {

    private final ElectricalMeasurementRepository measurementRepository;

    @Autowired
    public ElectricalMeasurementServiceImpl(ElectricalMeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    // metodo para obtener las medidicones y el consumo, tanto para client como para backoffice)
    @Override
    public MeasurementsDTO getElectricalMeasurements(String documentNumber, LocalDateTime from, LocalDateTime to){

        List<MeasurementView> measurements = this.measurementRepository
                .findMeasurementByDocumentNumberAndDate(documentNumber, from, to);
        if(measurements.size()>0){

            ElectricalMeterResponseDTO electricalMeter = ElectricalMeterResponseDTO.builder()
                    .electricalMeter(measurements.get(0).getElectricalMeter())
                    .address(measurements.get(0).getAddress())
                    .build();

            UserResponseDTO user = UserResponseDTO.builder()
                    .fullName(measurements.get(0).getFullName())
                    .documentNumber(measurements.get(0).getDocumentNumber())
                    .electricalMeters(Arrays.asList(electricalMeter))
                    .build();

            List<MeasurementDTO> measurementsDto = new ArrayList<>();

            measurements.forEach(
                    m -> {
                        measurementsDto.add(MeasurementDTO.builder()
                                .measurementDate(m.getMeasurementDate().toString())
                                .measurement(m.getMeasurement())
                                .build());
                    }
            );

            return MeasurementsDTO.builder()
                    .client(user)
                    .measurements(measurementsDto)
                    .build();
        }else {
            throw new ValidationException("Measurements not found");
        }
    }

    @Override
    public EnergyConsumptionDTO getEnergyConsumption(String documentNumber, LocalDateTime from, LocalDateTime to){

        EnergyConsumptionView energyConsumption = this.measurementRepository
                .findEnergyConsumptionByDocumentNumberAndDate(documentNumber, from, to)
                .orElseThrow(() -> new NotFoundException("Energy consuption not found"));

        ElectricalMeterResponseDTO electricalMeter = ElectricalMeterResponseDTO.builder()
                .electricalMeter(energyConsumption.getElectricalMeter())
                .address(energyConsumption.getAddress())
                .build();

        UserResponseDTO user = UserResponseDTO.builder()
                .fullName(energyConsumption.getFullName())
                .documentNumber(energyConsumption.getDocumentNumber())
                .electricalMeters(Arrays.asList(electricalMeter))
                .build();

        return EnergyConsumptionDTO.builder()
                .user(user)
                .measurementTotalKWH(energyConsumption.getTotalEnergyConsumption())
                .totalAmount(energyConsumption.getTotalAmount())
                .build();
    }

}
