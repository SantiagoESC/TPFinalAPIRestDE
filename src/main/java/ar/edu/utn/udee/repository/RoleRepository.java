package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleById(long id);

	Optional<Role> findByRoleName(String roleName);

}
