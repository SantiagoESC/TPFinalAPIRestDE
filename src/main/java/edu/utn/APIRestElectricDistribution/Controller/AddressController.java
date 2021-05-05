package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.Address;
import edu.utn.APIRestElectricDistribution.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/address")
@RestController
public class AddressController {

    //region Properties
    private final AddressService addressService;
    //endregion

    //region CONSTRUCTOR
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    //endregion

    //region GET
    @GetMapping("/")
    public List<Address>findAll(){
        return this.addressService.GetAll();
    }

    @GetMapping("/{id}")
    public Address GetById(@PathVariable("id")Integer id) throws Throwable{
        return this.addressService.GetById(id);
    }

    @GetMapping("/{zipCode}")
    public List<Address> GetByZipCode(@PathVariable("zipCode")String zipCode)throws Throwable{
        return this.addressService.GetByZipCode(zipCode);
    }

    @GetMapping("{city}")
    public List<Address> GetByCity(@PathVariable("city")String city)throws Throwable{
        return this.addressService.GetByCity(city);
    }
    //endregion

    //region POST
    @PutMapping("/id")
    public void Update(@PathVariable("id")Integer id, @RequestBody Address address) throws Throwable{

        Address address1 = this.addressService.GetById(id);
        address.setIdAddress(address1.getIdAddress());
        this.addressService.Update(address);
    }

    @PostMapping("/")
    public void PostAddress(@RequestBody Address address){this.addressService.PostAddress(address);}
    //endregion

    //region DELETE
    @DeleteMapping("/id")
    public void Delete(@PathVariable("id") Integer id) throws Throwable{

        Address address = this.addressService.GetById(id);
        this.addressService.Delete(address);
    }
    //endregion

}
