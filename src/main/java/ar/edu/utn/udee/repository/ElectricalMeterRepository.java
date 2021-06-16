package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.models.ElectricalMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectricalMeterRepository extends JpaRepository<ElectricalMeter,Integer> {
    Optional<ElectricalMeter> findBySerialNumber(String serialNumber);
}
