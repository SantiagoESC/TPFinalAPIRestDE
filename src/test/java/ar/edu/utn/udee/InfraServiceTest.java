package ar.edu.utn.udee;

import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import ar.edu.utn.udee.repository.ElectricalMeasurementRepository;
import ar.edu.utn.udee.repository.ElectricalMeterRepository;
import ar.edu.utn.udee.service.InfraService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.TestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfraServiceTest {

    private static final String SERIAL_NUMBER = "ABC-1234";

    @Mock
    private ElectricalMeterRepository electricalMeterRepository;
    @Mock
    private ElectricalMeasurementRepository electricalMeasurementRepository;
    @InjectMocks
    private InfraService infraService;

    @DisplayName("Test addMeasurement - OK")
    @Test
    void whenWantToAddMeasurementThenReturnNewMeasure(){

        when(this.electricalMeterRepository.findBySerialNumber(SERIAL_NUMBER))
                .thenReturn(Optional.of(TestUtils.buildElectricalMeter()));

        when(this.electricalMeasurementRepository.save(TestUtils.bulidElectricalMeasurement()))
                .thenReturn(TestUtils.bulidElectricalMeasurement());

        ElectricalMeasurement measurementResult = this.infraService.addMeasurement(TestUtils.buildMeasurementRequestDTO());

        assertEquals(TestUtils.bulidElectricalMeasurement(), measurementResult);
    }

    @DisplayName("Test addMeasurement - NotFoundException")
    @Test
    void whenWantToAddMeasurementThenReturnNotFoundException(){

        assertThrows(NotFoundException.class,
                () -> this.infraService.addMeasurement(TestUtils.buildMeasurementRequestDTO()));
    }

}