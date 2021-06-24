package ar.edu.utn.udee.controller.backoffice;

import ar.edu.utn.udee.dto.request.ElectricalMeterRequestDTO;
import ar.edu.utn.udee.dto.response.ElectricalMeterResponseDTO;
import ar.edu.utn.udee.service.ElectricalMeterService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/backoffice/electrical-meters")
@PreAuthorize("hasRole('ROLE_BACKOFFICE')") // NOSONAR
public class ElectricalMeterController {

    private final ElectricalMeterService electricalMeterService;

    @Autowired
    public ElectricalMeterController(ElectricalMeterService electricalMeterService) {
        this.electricalMeterService = electricalMeterService;
    }

    @GetMapping(value = "/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ElectricalMeterResponseDTO>> getElectricalMeterByDocumentNumber(
            @PathVariable("dni") String documentNumber){

        if(Strings.isBlank(documentNumber)){
            throw new ValidationException("Document Number is mandatory");
        }
        List<ElectricalMeterResponseDTO> responsesDTO = this.electricalMeterService
                .getElectricalMeterByDocumentNumber(documentNumber);
        return new ResponseEntity<>(responsesDTO, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricalMeterResponseDTO> registerElectricalMeter(
            @Valid @RequestBody ElectricalMeterRequestDTO requestDTO){

        ElectricalMeterResponseDTO responseDTO = this.electricalMeterService.saveElectricalMeterForClient(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
