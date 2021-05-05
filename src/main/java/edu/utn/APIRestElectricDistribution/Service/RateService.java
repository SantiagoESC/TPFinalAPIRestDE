package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.Rate;
import edu.utn.APIRestElectricDistribution.Repository.RateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService{

    //region Properties
    private final RateRepository rateRepository;
    //endregion

    //region Constructor
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }
    //endregion

    //region Get
    public List<Rate> GetAll() {
        return rateRepository.findAll();
    }

    public Rate GetById(Integer id) throws Throwable {
        return this.rateRepository.findById(id).orElseThrow(Throwable::new);
    }
    //endregion


    //region Update
    public void Update(Rate rate) {
        this.rateRepository.save(rate);
    }

    public void PostRate(Rate rate) {
        this.rateRepository.save(rate);
    }
    //endregion

    //region Delete
    public void Delete(Rate rate) {
        this.rateRepository.delete(rate);
    }
    //endregion

}
