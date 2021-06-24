package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.request.MeasurementRequestDTO;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import ar.edu.utn.udee.repository.ElectricalMeasurementRepository;
import ar.edu.utn.udee.repository.ElectricalMeterRepository;
import ar.edu.utn.udee.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static ar.edu.utn.udee.utils.TestUtils.buildMeasurementRequestDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfraServiceImplTest {

    private static final String SERIAL_NUMBER = "ABC-1234";

    @Mock
    private ElectricalMeterRepository electricalMeterRepository;
    @Mock
    private ElectricalMeasurementRepository electricalMeasurementRepository;
    @InjectMocks
    private InfraServiceImpl infraService;

    @DisplayName("Test addMeasurement - OK")
    @Test
    void whenWantToAddMeasurementThenReturnNewMeasure(){

        ElectricalMeasurement expectedElectricalMeasurement = TestUtils.bulidElectricalMeasurement();

        when(this.electricalMeterRepository.findBySerialNumber(SERIAL_NUMBER))
                .thenReturn(Optional.of(TestUtils.buildElectricalMeter()));
        when(this.electricalMeasurementRepository.save(expectedElectricalMeasurement))
                .thenReturn(expectedElectricalMeasurement);

        ElectricalMeasurement measurementResult = this.infraService
                .addMeasurement(TestUtils.buildMeasurementRequestDTO());

        assertEquals(expectedElectricalMeasurement, measurementResult);
    }

    @DisplayName("Test addMeasurement - NotFoundException")
    @Test
    void whenWantToAddMeasurementThenReturnNotFoundException(){

        MeasurementRequestDTO measurementRequestDTO = TestUtils.buildMeasurementRequestDTO();

        Exception exception = assertThrows(NotFoundException.class,
                () -> this.infraService.addMeasurement(measurementRequestDTO)
        );

        String expectedMessage = "Electrical Meter Not Found with serial number " + buildMeasurementRequestDTO().getSerialNumber();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}