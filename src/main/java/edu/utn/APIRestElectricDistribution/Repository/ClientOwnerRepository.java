package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.ClientOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOwnerRepository extends JpaRepository<ClientOwner,Integer> {
    List<ClientOwner> findByFirstNameClientOwner(String firstNameClientOwner);

}
