package ar.edu.utn.udee.controller.backoffice;

import ar.edu.utn.udee.dto.request.RateDTO;
import ar.edu.utn.udee.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/backoffice/rates")
@PreAuthorize("hasRole('ROLE_BACKOFFICE')") // NOSONAR
public class RateController {

    public final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping()
    public ResponseEntity<RateDTO> addRate(@RequestBody RateDTO rate){
        RateDTO rateDto = this.rateService.saveRate(rate);
        return new ResponseEntity<>(rateDto, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<RateDTO> updateRate(@RequestBody RateDTO rate){
        RateDTO rateDto = this.rateService.updateRate(rate);
        return new ResponseEntity<>(rateDto, HttpStatus.OK);
    }

    @DeleteMapping("/{typeRate}")
    public ResponseEntity<RateDTO> deleteRate(@PathVariable("typeRate") String typeRate){

        if (typeRate.isEmpty()){
            throw new ValidationException("Type Rate required");
        }
        this.rateService.deleteRate(typeRate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
