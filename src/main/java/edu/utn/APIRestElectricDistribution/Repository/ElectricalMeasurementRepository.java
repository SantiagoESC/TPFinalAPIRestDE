package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.ElectricalMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ElectricalMeasurementRepository extends JpaRepository<ElectricalMeasurement, Integer> {
    //List<ElectricalMeasurement>findElectricalMeasurementByDateRange(Date initDate, Date finalDate);
}
