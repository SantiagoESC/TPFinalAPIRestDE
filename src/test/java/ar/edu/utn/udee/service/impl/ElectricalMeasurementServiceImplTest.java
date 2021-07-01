package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.response.EnergyConsumptionDTO;
import ar.edu.utn.udee.dto.response.MeasurementsDTO;
import ar.edu.utn.udee.dto.view.EnergyConsumptionView;
import ar.edu.utn.udee.dto.view.MeasurementView;
import ar.edu.utn.udee.repository.ElectricalMeasurementRepository;
import ar.edu.utn.udee.service.ElectricalMeasurementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElectricalMeasurementServiceImplTest {

    @Mock
    private MeasurementView measurementView;
    @Mock
    private EnergyConsumptionView energyConsumptionView;
    @Mock
    private ElectricalMeasurementRepository measurementRepository;
    @InjectMocks
    private ElectricalMeasurementServiceImpl measurementService;

    @Test
    void getElectricalMeasurementsOk() {

        String documentNumber = "32165498";
        LocalDateTime dateTimeFrom = LocalDateTime.now().minusDays(2L);
        LocalDateTime dateTimeTo = LocalDateTime.now();

        when(this.measurementView.getMeasurement()).thenReturn(2.5);
        when(this.measurementView.getMeasurementDate()).thenReturn(dateTimeTo);

        when(this.measurementRepository.findMeasurementByDocumentNumberAndDate(documentNumber, dateTimeFrom, dateTimeTo))
                .thenReturn(Arrays.asList(measurementView));

        MeasurementsDTO measurementsDTO = this.measurementService.getElectricalMeasurements(documentNumber, dateTimeFrom, dateTimeTo);

        assertEquals(measurementView.getMeasurement(), measurementsDTO.getMeasurements().get(0).getMeasurement());
        assertEquals(measurementView.getMeasurementDate().toString(), measurementsDTO.getMeasurements().get(0).getMeasurementDate());
    }

    @Test
    void getEnergyConsumptionOk() {

        String documentNumber = "32165498";
        LocalDateTime dateTimeFrom = LocalDateTime.now().minusDays(2L);
        LocalDateTime dateTimeTo = LocalDateTime.now();

        when(this.energyConsumptionView.getTotalEnergyConsumption()).thenReturn(25.69);
        when(this.energyConsumptionView.getTotalAmount()).thenReturn(350.55);

        when(this.measurementRepository.findEnergyConsumptionByDocumentNumberAndDate(documentNumber, dateTimeFrom, dateTimeTo))
                .thenReturn(Optional.of(energyConsumptionView));

        EnergyConsumptionDTO energyConsumptionDTO = this.measurementService.getEnergyConsumption(documentNumber, dateTimeFrom, dateTimeTo);

        assertEquals(this.energyConsumptionView.getTotalEnergyConsumption(), energyConsumptionDTO.getMeasurementTotalKWH());
        assertEquals(this.energyConsumptionView.getTotalAmount(), energyConsumptionDTO.getTotalAmount());
    }

}