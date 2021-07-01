package ar.edu.utn.udee.controller.web;

import ar.edu.utn.udee.config.security.CurrentUser;
import ar.edu.utn.udee.dto.response.BillsResponseDTO;
import ar.edu.utn.udee.dto.response.EnergyConsumptionDTO;
import ar.edu.utn.udee.dto.response.MeasurementsDTO;
import ar.edu.utn.udee.service.BillsService;
import ar.edu.utn.udee.service.ElectricalMeasurementService;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/web")
@PreAuthorize("hasRole('ROLE_CLIENT')") // NOSONAR
public class WebController {

    private final BillsService billsService;
    private final ElectricalMeasurementService measurementService;

    @Autowired
    public WebController(BillsService billsService, ElectricalMeasurementService measurementService) {
        this.billsService = billsService;
        this.measurementService = measurementService;
    }

    //Consulta de mediciones por rango de fechas
    @GetMapping("/electrical-measurements")
    public ResponseEntity<MeasurementsDTO> getMeasurements(@CurrentUser @Parameter(hidden = true) UserDetails currentUser,
                                          @RequestParam("from") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") String from,
                                          @RequestParam("to") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss:") String to){

        if (Strings.isBlank(from) || Strings.isBlank(to)){
            throw new ValidationException("date required");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
        LocalDateTime toDate = LocalDateTime.parse(to, formatter);

        MeasurementsDTO measurements = this.measurementService
                .getElectricalMeasurements(currentUser.getUsername(), fromDate, toDate);
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

    //Consulta de facturas por rango de fechas.
    //Consulta de deuda (Facturas impagas)
    @GetMapping("/bills")
    public ResponseEntity<List<BillsResponseDTO>> getBills(@CurrentUser @Parameter(hidden = true) UserDetails currentUser,
                                                           @RequestParam("paid") boolean isPaid ,
                                                           @RequestParam("from") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") LocalDateTime from,
                                                           @RequestParam("to") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") LocalDateTime to) {
        //TODO fechas
        List<BillsResponseDTO> bills = this.billsService
                .getBillsByDocumentNumber(currentUser.getUsername(), isPaid);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    //Consulta de consumo por rango de fechas :
    //    (el usuario va a ingresar un rango de fechas y
    //   quiere saber cuánto consumió en ese periodo en Kwh y dinero)
    @GetMapping(value = "/energy-consumption", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnergyConsumptionDTO> getEnergyConsumption(@CurrentUser @Parameter(hidden = true) UserDetails currentUser,
                                                                     @RequestParam("from") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") String from,
                                                                     @RequestParam("to") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss:") String to){

        if (Strings.isBlank(from) || Strings.isBlank(to)){
            throw new ValidationException("date required");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
        LocalDateTime toDate = LocalDateTime.parse(to, formatter);

        EnergyConsumptionDTO energyConsumption = this.measurementService
                .getEnergyConsumption(currentUser.getUsername(), fromDate, toDate);
        return new ResponseEntity<>(energyConsumption, HttpStatus.OK);
    }

}
