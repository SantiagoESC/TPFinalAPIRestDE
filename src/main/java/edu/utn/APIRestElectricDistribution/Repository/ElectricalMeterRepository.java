package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.ElectricalMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricalMeterRepository extends JpaRepository<ElectricalMeter,Integer> {

    //List<Measurer> findByModel(String );
}
