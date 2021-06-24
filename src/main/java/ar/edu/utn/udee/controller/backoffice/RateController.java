package ar.edu.utn.udee.controller.backoffice;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backoffice/rates")
@PreAuthorize("hasRole('ROLE_BACKOFFICE')") // NOSONAR
public class RateController {

    //  Alta, baja y modificación de tarifas.





    //GET


    //POST


    //PUT


    //DELETE

}
