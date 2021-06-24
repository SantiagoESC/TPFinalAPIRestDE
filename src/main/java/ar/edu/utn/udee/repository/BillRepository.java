package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.dto.response.BillsResponseDTO;
import ar.edu.utn.udee.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "SELECT documentNumber, fullName, electricalMeter, address, rateType, priceByRate, " +
            "measurementDateInitial, measurementInitial, measurementDateFinal, measurementFinal, expirationDate, " +
            "measurementTotalKWH, totalAmount, isPaid FROM v_bills " +
            "WHERE documentNumber = :documentNumber AND isPaid = :isPaid; ", nativeQuery = true)
    List<BillsResponseDTO> findByDocumentNumber(@Param("documentNumber")String documentNumber,
                                                @Param("isPaid")boolean isPaid);

}
