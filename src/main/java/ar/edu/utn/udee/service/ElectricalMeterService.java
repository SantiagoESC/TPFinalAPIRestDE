package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.ElectricalMeterRequestDTO;
import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;

import java.util.List;

public interface ElectricalMeterService {

    ElectricalMeterResponseDTO saveElectricalMeterForClient(ElectricalMeterRequestDTO requestDTO);

    List<ElectricalMeterResponseDTO> getElectricalMeterByDocumentNumber(String documentNumber);

}
