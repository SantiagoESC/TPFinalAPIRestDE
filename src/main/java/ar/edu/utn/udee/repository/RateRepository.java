package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

    Optional<Rate> findByTypeRate(String typeRate);
}
