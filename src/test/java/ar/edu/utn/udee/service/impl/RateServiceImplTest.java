package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.request.RateDTO;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.Rate;
import ar.edu.utn.udee.repository.RateRepository;
import ar.edu.utn.udee.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateServiceImplTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RateServiceImpl rateService;

    @Test
    void saveRateOk() {
        RateDTO rateExpected = TestUtils.buildRateDTO();

        when(this.rateRepository.findByTypeRate(rateExpected.getTypeRate())).thenReturn(Optional.empty());
        when(this.rateRepository.save(TestUtils.buildRate())).thenReturn(TestUtils.buildRate());

        RateDTO actualRate = this.rateService.saveRate(TestUtils.buildRateDTO());

        assertEquals(rateExpected.getTypeRate(), actualRate.getTypeRate());
    }


    @Test
    void whenSaveRateThenThrowException() {

        when(this.rateRepository.findByTypeRate("TYPE_RATE")).thenReturn(Optional.of(TestUtils.buildRate()));

        Exception exception = assertThrows(ValidationException.class,
                () -> this.rateService.saveRate(TestUtils.buildRateDTO())
        );

        String expectedMessage = "Rate Already exist!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateRateOk() {

        RateDTO rateExpected = TestUtils.buildRateDTO();

        when(this.rateRepository.findByTypeRate(rateExpected.getTypeRate()))
                .thenReturn(Optional.of(TestUtils.buildRate()));
        when(this.rateRepository.save(TestUtils.buildRate()))
                .thenReturn(TestUtils.buildRate());

        RateDTO actualRate = this.rateService.updateRate(TestUtils.buildRateDTO());

        assertEquals(rateExpected.getTypeRate(), actualRate.getTypeRate());
    }

    @Test
    void updateRateThrowException(){

        when(this.rateRepository.findByTypeRate("TYPE_RATE")).thenReturn(Optional.empty());

        RateDTO rateDTO = TestUtils.buildRateDTO();

        Exception exception = assertThrows(NotFoundException.class,
                () -> this.rateService.updateRate(rateDTO)
        );

        String expectedMessage = "Rate not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteRateOk() {

        when(this.rateRepository.findByTypeRate("PUBLIC_RATE")).thenReturn(Optional.of(TestUtils.buildRate()));

        doNothing().when(this.rateRepository).delete(isA(Rate.class));

        this.rateService.deleteRate("PUBLIC_RATE");

        verify(this.rateRepository, times(1)).findByTypeRate("PUBLIC_RATE");
    }

}