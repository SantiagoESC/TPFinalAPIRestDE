package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.Address;
import edu.utn.APIRestElectricDistribution.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    //Properties egion
    private final AddressRepository addressRepository;

    //Constructor region
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    //Get region
    public List<Address>GetAll(){return addressRepository.findAll();}

    public Address GetById(Integer id) throws Throwable{
        return this.addressRepository.findById(id).orElseThrow(Throwable::new);
    }

    public List<Address> GetByZipCode(String zipCode){
        return this.addressRepository.findByZipCode(zipCode);
    }

    public List<Address>GetByCity(String city){
        return this.addressRepository.findByCity(city);
    }

    //Update region
    public void Update(Address address){
        this.addressRepository.save(address);
    }

    public void PostAddress(Address address){this.addressRepository.save(address);}

    //Delete region
    public void Delete(Address address){this.addressRepository.delete(address);}

}
