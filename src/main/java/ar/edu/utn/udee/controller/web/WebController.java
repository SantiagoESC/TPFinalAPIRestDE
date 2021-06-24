package ar.edu.utn.udee.controller.web;

import ar.edu.utn.udee.config.security.CurrentUser;
import ar.edu.utn.udee.dto.response.BillsResponseDTO;
import ar.edu.utn.udee.service.BillsService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web")
@PreAuthorize("hasRole('ROLE_CLIENT')") // NOSONAR
public class WebController {

    private final BillsService billsService;

    @Autowired
    public WebController(BillsService billsService) {
        this.billsService = billsService;
    }
    /*
    - Consulta de facturas por rango de fechas.

    - Consulta de deuda (Facturas impagas)

    - Consulta de consumo por rango de fechas
        (el usuario va a ingresar un rango de fechas y
        quiere saber cuánto consumió en ese periodo en Kwh y dinero)

    - Consulta de mediciones por rango de fechas
     */


    //Consulta de deuda (Facturas impagas)
    @GetMapping("/bills")
    public ResponseEntity<List<BillsResponseDTO>> getCurrentUser(
            @CurrentUser @Parameter(hidden = true) UserDetails currentUser, @PathVariable("paid") boolean isPaid ) {

        List<BillsResponseDTO> billsNotPaid = this.billsService
                .getBillsByDocumentNumber(currentUser.getUsername(), isPaid);

        return new ResponseEntity<>(billsNotPaid, HttpStatus.OK);
    }







}
