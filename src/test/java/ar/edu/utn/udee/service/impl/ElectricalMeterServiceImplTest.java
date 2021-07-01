package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.request.ElectricalMeterRequestDTO;
import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;
import ar.edu.utn.udee.mapper.ElectricalMeterMapper;
import ar.edu.utn.udee.models.Address;
import ar.edu.utn.udee.models.ElectricalMeter;
import ar.edu.utn.udee.models.Rate;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.repository.AddressRepository;
import ar.edu.utn.udee.repository.ElectricalMeterRepository;
import ar.edu.utn.udee.service.UserService;
import ar.edu.utn.udee.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElectricalMeterServiceImplTest {

    @Mock
    private UserService userService;
    @Mock
    private ElectricalMeterRepository electricalMeterRepository;
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private ElectricalMeterServiceImpl electricalMeterService;

    @Test
    void saveElectricalMeterForClientOk() {

        ElectricalMeterResponseDTO responseExpect = TestUtils.buildElectricalMeterResponseDTO();

        when(this.userService.getUserByDocumentNumber("123456789")).thenReturn(TestUtils.buildUser());
        when(this.addressRepository.save(isA(Address.class))).thenReturn(TestUtils.buildAddress());
        when(this.electricalMeterRepository.save(isA(ElectricalMeter.class)))
                .thenReturn(TestUtils.buildElectricalMeter());

        ElectricalMeterResponseDTO actualResponse =
                this.electricalMeterService.saveElectricalMeterForClient(TestUtils.buildElectricalMeterRequestDTO());

        assertEquals(responseExpect.getElectricalMeter(), actualResponse.getElectricalMeter());
    }

}