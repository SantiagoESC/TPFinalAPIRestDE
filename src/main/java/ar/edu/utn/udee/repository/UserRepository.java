package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByDocumentNumber(String documentNumber);

	User findById(long id);

}
