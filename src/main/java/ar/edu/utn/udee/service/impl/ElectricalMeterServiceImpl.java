package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.request.ElectricalMeterRequestDTO;
import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;
import ar.edu.utn.udee.mapper.ElectricalMeterMapper;
import ar.edu.utn.udee.models.ElectricalMeter;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.repository.AddressRepository;
import ar.edu.utn.udee.repository.ElectricalMeterRepository;
import ar.edu.utn.udee.service.ElectricalMeterService;
import ar.edu.utn.udee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricalMeterServiceImpl implements ElectricalMeterService {

    private final UserService userService;
    private final ElectricalMeterRepository electricalMeterRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ElectricalMeterServiceImpl(UserService userService, ElectricalMeterRepository electricalMeterRepository,
                                      AddressRepository addressRepository) {
        this.userService = userService;
        this.electricalMeterRepository = electricalMeterRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public ElectricalMeterResponseDTO saveElectricalMeterForClient(ElectricalMeterRequestDTO requestDTO) {
        
        User user = this.userService.getUserByDocumentNumber(requestDTO.getDocumentNumber());

        ElectricalMeter electricalMeterToSave = ElectricalMeterMapper.toEntity(requestDTO, user);

        this.addressRepository.save(electricalMeterToSave.getAddress());

        ElectricalMeter electricalMeterSaved = this.electricalMeterRepository.save(electricalMeterToSave);
        
        return ElectricalMeterMapper.toResponse(electricalMeterSaved) ;
    }

    @Override
    public List<ElectricalMeterResponseDTO> getElectricalMeterByDocumentNumber(String documentNumber) {

        User user = this.userService.getUserByDocumentNumber(documentNumber);

        List<ElectricalMeter> electricalMeters = user.getElectricalMeters();

        return electricalMeters.stream().map(ElectricalMeterMapper::toResponse)
                .collect(Collectors.toList());
    }

}
