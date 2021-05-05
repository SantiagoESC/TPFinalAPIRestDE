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

    //region Properties
    private final BillService billService;
    //endregion

    //region Constructor
    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
    //endregion

    //region Get
    @GetMapping("/")
    public List<Bill>findAll(){
        return this.billService.GetAll();
    }

    @GetMapping("/{id}")
    public Bill GetById(@PathVariable("id")Integer id) throws Throwable{
        return this.billService.GetById(id);
    }
    //endregion

    //region Post
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
    //endregion

    //region Delete
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable("id")Integer id) throws Throwable{
        Bill bill = this.billService.GetById(id);
        this.billService.Delete(bill);
    }
    //endregion



}
