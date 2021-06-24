package ar.edu.utn.udee.controller.backoffice;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backoffice")
@PreAuthorize("hasRole('ROLE_BACKOFFICE')") // NOSONAR
public class BackOfficeController {

    //  Consulta de facturas impagas por cliente y domicilio.
    //  Consulta 10 clientes más consumidores en un rango de fechas.
    //  Consulta de mediciones de un domicilio por rango de fechas



    //  /clients/{dni}/address/{idAddress}/bills?paid=false




}
