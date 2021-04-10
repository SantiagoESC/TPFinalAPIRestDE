package edu.utn.APIRestElectricDistribution.Service;

//import edu.utn.APIRestElectricDistribution.Domain.ClientOwner;
//import edu.utn.APIRestElectricDistribution.Domain.Rate;
//import edu.utn.APIRestElectricDistribution.Repository.RateRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;

//@Service
//public class RateService{
//
//    //Properties region
//    private final RateRepository rateRepository;
//
//    //Constructor region
//    public RateService(RateRepository rateRepository) {
//        this.rateRepository = rateRepository;
//    }
//
//    //Get region
//    public List<Rate> GetAll() {
//        return rateRepository.findAll();
//    }
//
//    public Rate GetById(Integer id) throws Throwable {
//        return this.rateRepository.findById(id).orElseThrow(Throwable::new);
//    }
//
//    //Update region
//    public void Update(Rate rate) {
//        this.rateRepository.save(rate);
//    }
//
//    public void PostRate(Rate rate) {
//        this.rateRepository.save(rate);
//    }
//
//    //Delete region
//    public void Delete(Rate rate) {
//        this.rateRepository.delete(rate);
//    }
//
//}
