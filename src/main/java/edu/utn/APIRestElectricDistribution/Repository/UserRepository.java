package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Procedure(procedureName = "sp_insert_user_client", outputParameterName = "pIdUser")
    Long addClient(@Param("pFirstName")String firstName, @Param("pLastName")String lastName, @Param("pIDCardNumber")String IDCardNumber, @Param("pPassword")String password);

    @Query(value = "SELECT u FROM User u WHERE u.IDCardNumber = :IDCardNumber AND u.password = :password")
    User getUserByIDCardNumberAndPassword(@Param("IDCardNumber")String IDCardNumber, @Param("password") String password);

    @Query(value = "SELECT u FROM User u WHERE u.IDCardNumber = :IDCardNumber")
    User findByIDCardNumber(String IDCardNumber);

    @Query(value = "SELECT u.* FROM users u WHERE u.user_role = 'client' AND u.enabled = true", nativeQuery = true)
    List<User> findAllClients();

    @Procedure(procedureName = "sp_update_user", outputParameterName = "pIdUser")
    Long updateClient(@Param("pFirstName")String firstName, @Param("pLastName")String lastName);

    @Procedure(procedureName = "sp_delete_user")
    void removeClientByIDCardNumber(@Param("pIDCardNumber") String IDCardNumber);

}
