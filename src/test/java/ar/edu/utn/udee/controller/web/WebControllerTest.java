package ar.edu.utn.udee.controller.web;

import ar.edu.utn.udee.dto.response.MeasurementDTO;
import ar.edu.utn.udee.dto.response.MeasurementsDTO;
import ar.edu.utn.udee.dto.response.UserResponseDTO;
import ar.edu.utn.udee.service.BillsService;
import ar.edu.utn.udee.service.ElectricalMeasurementService;
import ar.edu.utn.udee.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WebControllerTest {

    @Mock
    private BillsService billsService;
    @Mock
    private ElectricalMeasurementService measurementService;
    @InjectMocks
    private WebController webController;

    @Test
    void getMeasurements() {
    }
}