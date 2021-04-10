package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.ElectricalMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricalMeasurementRepository extends JpaRepository<ElectricalMeasurement, Integer> {
}
