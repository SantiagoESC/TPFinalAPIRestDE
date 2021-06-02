package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByZipCode(String zipCode);
    List<Address> findByCity(String city);
}
