package ar.edu.utn.udee.controller.infra;

import ar.edu.utn.udee.dto.request.MeasurementRequestDTO;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import ar.edu.utn.udee.service.InfraService;
import ar.edu.utn.udee.utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infra")
public class InfraController {

    private final InfraService infraService;

    @Autowired
    public InfraController(InfraService infraService) {
        this.infraService = infraService;
    }

    @PostMapping("/electrical-measurement")
    public ResponseEntity<Void> addMeasurement(@RequestBody MeasurementRequestDTO mesurement){

        ElectricalMeasurement measurement = this.infraService.addMeasurement(mesurement);
        return ResponseEntity.created(UriUtils.getLocation(measurement.getId())).build();
    }

}
