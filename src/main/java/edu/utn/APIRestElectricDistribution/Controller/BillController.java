package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.Bill;
import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/bill")
@RestController
public class BillController {

    //Properties region
    private final BillService billService;

    //Constructor region
    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    //Get region
//    @GetMapping("/")
//    public List<Bill> findAll(@RequestParam(value = "username", defaultValue = "*", required = false) String username){
//        return (username.equals("*")) ? this.billService.GetAll() : this.billService.GetByUsername(username);
//    }

    @GetMapping("/")
    public List<Bill>findAll(){
        return this.billService.GetAll();
    }

    @GetMapping("/{id}")
    public Bill GetById(@PathVariable("id")Integer id) throws Throwable{
        return this.billService.GetById(id);
    }

    //Post region
    @PutMapping("/{id}")
    public void Update(@PathVariable("id")Integer id,@RequestBody Bill bill) throws Throwable{
        Bill bill1 = this.billService.GetById(id);
        bill.setIdBill(bill1.getIdBill());
        this.billService.Update(bill);
    }

    @PostMapping("/")
    public void PostUser(@RequestBody Bill bill){
        this.billService.PostBill(bill);
    }

    //Delete region
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable("id")Integer id) throws Throwable{
        Bill bill = this.billService.GetById(id);
        this.billService.Delete(bill);
    }



}
