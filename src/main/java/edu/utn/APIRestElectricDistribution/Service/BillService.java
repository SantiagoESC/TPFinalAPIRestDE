package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.Bill;
import edu.utn.APIRestElectricDistribution.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    //region Properties
    private final BillRepository billRepository;
    //endregion

    //region Constructor
    @Autowired
    public BillService(BillRepository billRepository){
        this.billRepository = billRepository;
    }
    //endregion

    //region GET
    public List<Bill>GetAll(){
        return this.billRepository.findAll();
    }

    public Bill GetById(Integer id) throws Throwable{
        return this.billRepository.findById(id).orElseThrow(Throwable::new);
    }
    //endregion

    //region Update
    public void Update(Bill bill){
        this.billRepository.save(bill);
    }
    public void PostBill(Bill bill){
        this.billRepository.save(bill);
    }
    //endregion

    //region Delete
    public void Delete(Bill bill){
        this.billRepository.delete(bill);
    }
    //endregion

}
