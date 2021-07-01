package ar.edu.utn.udee.controller.backoffice;

import ar.edu.utn.udee.config.security.CurrentUser;
import ar.edu.utn.udee.dto.response.BillsResponseDTO;
import ar.edu.utn.udee.dto.response.EnergyConsumptionDTO;
import ar.edu.utn.udee.service.BillsService;
import ar.edu.utn.udee.service.ElectricalMeasurementService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backoffice")
@PreAuthorize("hasRole('ROLE_BACKOFFICE')") // NOSONAR
public class BackOfficeController {

    private final ElectricalMeasurementService measurementService;
    private final BillsService billsService;

    @Autowired
    public BackOfficeController(ElectricalMeasurementService measurementService, BillsService billsService) {
        this.measurementService = measurementService;
        this.billsService = billsService;
    }

    //TODO Consulta 10 clientes más consumidores en un rango de fechas.
    @GetMapping(value = "/clients/energy-consumption/top-10", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnergyConsumptionDTO>> getEnergyConsumption(@CurrentUser @Parameter(hidden = true) UserDetails currentUser,
                                                                           @RequestParam("from") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss") String from,
                                                                           @RequestParam("to") @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss:") String to){

        return null;
    }

    //Consulta de facturas impagas por cliente y domicilio.
    @GetMapping("/clients/{documentNumber}/bills")
    public ResponseEntity<List<BillsResponseDTO>> getBills(@CurrentUser @Parameter(hidden = true) UserDetails currentUser,
                                                           @PathVariable("documentNumber") String documentNumber ) {

        List<BillsResponseDTO> bills = this.billsService
                .getBillsByDocumentNumber(documentNumber, false);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }




    //TODO Consulta de mediciones de un domicilio por rango de fechas
    //  /clients/{dni}/address/{idAddress}/bills?paid=false





}
