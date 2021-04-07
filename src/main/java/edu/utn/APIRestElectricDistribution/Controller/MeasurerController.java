package edu.utn.APIRestElectricDistribution.Controller;


import edu.utn.APIRestElectricDistribution.Domain.Client;
import edu.utn.APIRestElectricDistribution.Domain.Measurer;
import edu.utn.APIRestElectricDistribution.Service.MeasurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/measurer")
@RestController
public class MeasurerController {

    //region Properties
    private final MeasurerService measurerService;
    //endregion

    //region Constructor
    @Autowired
    public MeasurerController(MeasurerService measurerService)
    {
        this.measurerService = measurerService;
    }
    //endregion

    //region GET
    @GetMapping("/")
    //@RequestMapping(path = "/",method = RequestMethod.GET)
    private List<Measurer> FindAll(){
        return  this.measurerService.GetAll();
    }
    @GetMapping("/{id}")
    private Measurer GetBySerialNumber(@PathVariable("id") Integer serial) throws Throwable{
        return  this.measurerService.GetById(serial);
    }
    //endregion
}
