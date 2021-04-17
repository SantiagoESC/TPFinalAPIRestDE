package edu.utn.APIRestElectricDistribution.Controller;


import edu.utn.APIRestElectricDistribution.Domain.ElectricalMeter;
import edu.utn.APIRestElectricDistribution.Service.ElectricalMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/electricalMeter")
@RestController
public class ElectricalMeterController {

    //region Properties
    private final ElectricalMeterService electricalMeterService;
    //endregion

    //region Constructor
    @Autowired
    public ElectricalMeterController(ElectricalMeterService electricalMeterService)
    {
        this.electricalMeterService = electricalMeterService;
    }
    //endregion

    //region GET
    @GetMapping("/")
    //@RequestMapping(path = "/",method = RequestMethod.GET)
    public List<ElectricalMeter> FindAll(){
        return  this.electricalMeterService.GetAll();
    }

    @GetMapping("/{id}")
    public ElectricalMeter GetBySerialNumber(@PathVariable("id") Integer serial) throws Throwable{
        return  this.electricalMeterService.GetById(serial);
    }
    //endregion
}
