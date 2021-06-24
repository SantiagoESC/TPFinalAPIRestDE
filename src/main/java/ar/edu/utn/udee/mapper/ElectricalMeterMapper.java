package ar.edu.utn.udee.mapper;

import ar.edu.utn.udee.dto.request.ElectricalMeterRequestDTO;
import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;
import ar.edu.utn.udee.models.Address;
import ar.edu.utn.udee.models.ElectricalMeter;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.utils.SerialNumberGenerator;
import org.apache.logging.log4j.util.Strings;

import java.util.Optional;

public class ElectricalMeterMapper {

    private ElectricalMeterMapper() {
        throw new IllegalStateException("Mapper class");
    }

    public static ElectricalMeterResponseDTO toResponse(ElectricalMeter electricalMeter) {

        final String floorAndDepartment = Optional.ofNullable(electricalMeter.getAddress().getFloor()).orElse(Strings.EMPTY)
                + "/" + Optional.ofNullable(electricalMeter.getAddress().getDepartment()).orElse(Strings.EMPTY) + ", ";
        final String address = electricalMeter.getAddress().getStreetName() + " " +
                electricalMeter.getAddress().getStreetNumber() + ", " + floorAndDepartment
                + electricalMeter.getAddress().getZipCode() + ", " + electricalMeter.getAddress().getCity();
        // Av Colon 1231, PB/4, 7600, Mar del Plata

        final String strElectricalMeter = electricalMeter.getModel() + ",  " + electricalMeter.getModel()
                + ",  " + electricalMeter.getSerialNumber();

        return ElectricalMeterResponseDTO.builder()
                .address(address)
                .electricalMeter(strElectricalMeter)
                .build();
    }

    public static ElectricalMeter toEntity(ElectricalMeterRequestDTO requestDTO, User user) {

        Address address = Address.builder()
                .streetName(requestDTO.getStreetName())
                .streetNumber(requestDTO.getStreetNumber())
                .floor(Optional.ofNullable(requestDTO.getFloor()).orElse(Strings.EMPTY))
                .department(Optional.ofNullable(requestDTO.getDepartment()).orElse(Strings.EMPTY))
                .zipCode(requestDTO.getZipCode())
                .city(requestDTO.getCity())
                .build();

        return ElectricalMeter.builder()
                .brand(requestDTO.getBrand())
                .model(requestDTO.getModel())
                .serialNumber(new SerialNumberGenerator().generate())
                .user(user)
                .address(address)
                .isEnabled(Boolean.TRUE)
                .build();
    }

}
