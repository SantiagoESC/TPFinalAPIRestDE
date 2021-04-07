package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.Measurer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurerRepository extends JpaRepository<Measurer,Integer> {

    //List<Measurer> findByModel(String model);
}
