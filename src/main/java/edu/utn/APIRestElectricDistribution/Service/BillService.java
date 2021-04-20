package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.Bill;
import edu.utn.APIRestElectricDistribution.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    //Properties region
    private final BillRepository billRepository;

    //Constructor region
    @Autowired
    public BillService(BillRepository billRepository){
        this.billRepository = billRepository;
    }

    //GET region
    public List<Bill>GetAll(){
        return this.billRepository.findAll();
    }

    public Bill GetById(Integer id) throws Throwable{
        return this.billRepository.findById(id).orElseThrow(Throwable::new);
    }

    //Update region
    public void Update(Bill bill){
        this.billRepository.save(bill);
    }
    public void PostBill(Bill bill){
        this.billRepository.save(bill);
    }

    //Delete region
    public void Delete(Bill bill){
        this.billRepository.delete(bill);
    }
}
