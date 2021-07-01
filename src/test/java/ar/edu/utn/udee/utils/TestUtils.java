package ar.edu.utn.udee.utils;

import ar.edu.utn.udee.dto.request.ElectricalMeterRequestDTO;
import ar.edu.utn.udee.dto.request.MeasurementRequestDTO;
import ar.edu.utn.udee.dto.request.RateDTO;
import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;
import ar.edu.utn.udee.models.Address;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import ar.edu.utn.udee.models.ElectricalMeter;
import ar.edu.utn.udee.models.Rate;
import ar.edu.utn.udee.models.Role;
import ar.edu.utn.udee.models.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TestUtils {

    private static final String SERIAL_NUMBER = "ABC-1234";
    private static final Double MEASUREMENT_KWH = 9.5;
    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final long USER_ID = 1L;
    private static final String USER_NAME = "Jorge";
    private static final String USER_FULL_NAME = USER_NAME + " " + USER_NAME;
    private static final String USER_DOCUMENT_NUMBER = "123456789";
    private static final String USER_PASS = "USER_PASS";

    public static User buildUser(){
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().roleName("ROLE_BACKOFFICE").id(1L).description("ROLE_DESC_BACKOFFICE").build());
        roles.add(Role.builder().roleName("ROLE_CLIENT").id(1L).description("ROLE_DESC_CLIENT").build());

        return User.builder()
                .id(USER_ID)
                .documentNumber(USER_DOCUMENT_NUMBER)
                .password(USER_PASS).roles(roles)
                .build();
    }

    public static Address buildAddress(){
        return Address.builder()
                .streetName("Av. Siempre Viva")
                .streetNumber(742)
                .floor(" ")
                .department(" ")
                .zipCode("7600")
                .city("Mar del Plata")
                .build();
    }

    public static ElectricalMeterRequestDTO buildElectricalMeterRequestDTO(){
        return ElectricalMeterRequestDTO.builder()
                .documentNumber(USER_DOCUMENT_NUMBER)
                .streetName("Av. Siempre Viva")
                .streetNumber(742)
                .zipCode("7600")
                .city("Mar del Plata")
                .floor(" ")
                .department(" ")
                .brand("BRAND")
                .model("MODEL")
                .build();
    }

    public static ElectricalMeterResponseDTO buildElectricalMeterResponseDTO(){
        return ElectricalMeterResponseDTO.builder()
                .electricalMeter("BRAND, MODEL, ABC-1234")
                .address("Av. Siempre Viva, 742, 7600, Mar del Plata")
                .build();
    }

    public static MeasurementRequestDTO buildMeasurementRequestDTO(){
        return MeasurementRequestDTO.builder()
                .serialNumber(SERIAL_NUMBER)
                .measurementKWH(MEASUREMENT_KWH)
                .date(DATE)
                .build();
    }

    public static ElectricalMeter buildElectricalMeter(){
        return ElectricalMeter.builder()
                .id(1L)
                .user(buildUser())
                .address(buildAddress())
                .serialNumber(SERIAL_NUMBER)
                .brand("BRAND")
                .model("MODEL")
                .build();
    }

    public static ElectricalMeasurement bulidElectricalMeasurement(){
        return ElectricalMeasurement.builder()
                .electricalMeter(buildElectricalMeter())
                .measurementKWH(MEASUREMENT_KWH)
                .measurementDateTime(DATE)
                .build();
    }


    public static RateDTO buildRateDTO(){
        return RateDTO.builder()
                .typeRate("TYPE_RATE")
                .price(2.5f)
                .build();
    }

    public static Rate buildRate(){
        return Rate.builder()
                .typeRate("TYPE_RATE")
                .price(2.5f)
                .build();
    }

}
