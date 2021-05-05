package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    //List<User> findByUsernameUser(String username);
}
