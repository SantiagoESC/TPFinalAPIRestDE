package ar.edu.utn.udee.utils;

import ar.edu.utn.udee.dto.request.MeasurementRequestDTO;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import ar.edu.utn.udee.models.ElectricalMeter;

import java.time.LocalDateTime;

public class TestUtils {

    private static final String SERIAL_NUMBER = "ABC-1234";
    private static final Double MEASUREMENT_KWH = 9.5;
    private static final LocalDateTime DATE = LocalDateTime.now();

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
                .serialNumber(SERIAL_NUMBER)
                .build();
    }

    public static ElectricalMeasurement bulidElectricalMeasurement(){
        return ElectricalMeasurement.builder()
                .electricalMeter(buildElectricalMeter())
                .measurementKWH(MEASUREMENT_KWH)
                .measurementDate(DATE)
                .build();
    }

}
