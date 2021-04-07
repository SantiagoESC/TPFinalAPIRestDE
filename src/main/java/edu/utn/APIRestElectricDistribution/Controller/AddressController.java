package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.Address;
import edu.utn.APIRestElectricDistribution.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/address/")
@RestController
public class AddressController {

    //Properties region
    private final AddressService addressService;

    //Constructor region
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    //Get region
//    @GetMapping("/")
//    private List<Address>findAllOrByZipCode(@RequestParam(value = "zipCode", defaultValue = "*", required = false) String zipCode){
//        return (zipCode.equals("*")) ? this.addressService.GetAll() : this.addressService.GetByZipCode(zipCode);
//    }

    @GetMapping("/{id}")
    private Address GetById(@PathVariable("id")Integer id) throws Throwable{

        return this.addressService.GetById(id);
    }

    //Post region
    @PutMapping("/id")
    private void Update(@PathVariable("id")Integer id, @RequestBody Address address) throws Throwable{

        Address address1 = this.addressService.GetById(id);
        address.setIdAddress(address1.getIdAddress());
        this.addressService.Update(address);
    }

    @PostMapping("/")
    private void PostAddress(@RequestBody Address address){this.addressService.PostAddress(address);}

    //Delete region
    @DeleteMapping("/id")
    private void Delete(@PathVariable("id") Integer id) throws Throwable{

        Address address = this.addressService.GetById(id);
        this.addressService.Delete(address);
    }

}
