package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.Measurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurerRepository extends JpaRepository<Measurer,Integer> {

    //List<Measurer> findByModel(String );
}
