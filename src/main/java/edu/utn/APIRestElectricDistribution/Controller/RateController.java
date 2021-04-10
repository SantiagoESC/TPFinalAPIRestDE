package edu.utn.APIRestElectricDistribution.Controller;

//import edu.utn.APIRestElectricDistribution.Domain.Rate;
//import edu.utn.APIRestElectricDistribution.Service.RateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;

//@RequestMapping("/api/rate")
//@RestController
//public class RateController {
//
//    //Properties region
//    private final RateService rateService;
//
//    //Constructor region
//    @Autowired
//    public RateController(RateService rateService){
//        this.rateService = rateService;
//    }
//
//    //Get region
//    @GetMapping("/{id}")
//    public Rate GetById(@PathVariable("id")Integer id) throws Throwable{
//        return this.rateService.GetById(id);
//    }
//
//    //Post region
//    @PutMapping("{id}")
//    public void Update(@PathVariable("id")Integer id, @RequestBody Rate rate) throws Throwable{
//        Rate rate1 = this.rateService.GetById(id);
//        rate.setIdRate(rate1.getIdRate());
//        this.rateService.Update(rate);
//    }
//
//    @PostMapping("/")
//    public void PostRate(@RequestBody Rate rate){
//        this.rateService.PostRate(rate);
//    }
//
//    //Delete region
//    @DeleteMapping("/{id}")
//    public void Delete(@PathVariable("id")Integer id) throws Throwable{
//        Rate rate = this.rateService.GetById(id);
//        this.rateService.Delete(rate);
//    }
//}
