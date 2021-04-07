package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.Client;
import edu.utn.APIRestElectricDistribution.Domain.Measurer;
import edu.utn.APIRestElectricDistribution.Repository.ClientRepository;
import edu.utn.APIRestElectricDistribution.Repository.MeasurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeasurerService {

    private final MeasurerRepository measurerRepository;

    @Autowired
    public MeasurerService(MeasurerRepository measurerRepository) {
        this.measurerRepository = measurerRepository;
    }

    //region GET
    public List<Measurer> GetAll() {
        return measurerRepository.findAll();
    }

    /*public List<Measurer> GetByModel(String model) {
        return this.measurerRepository.findByModel(model);
    }*/

    public Measurer GetById(Integer serial) throws Throwable {
        return this.measurerRepository.findById(serial).orElseThrow(Throwable::new);
    }

}
