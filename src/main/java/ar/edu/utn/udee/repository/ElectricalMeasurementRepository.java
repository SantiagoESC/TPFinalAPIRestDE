package ar.edu.utn.udee.repository;

import ar.edu.utn.udee.dto.view.EnergyConsumptionView;
import ar.edu.utn.udee.dto.view.MeasurementView;
import ar.edu.utn.udee.models.ElectricalMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ElectricalMeasurementRepository extends JpaRepository<ElectricalMeasurement, Integer> {

    @Query(value = "SELECT \n" +
            "\t    u.DOCUMENT_NUMBER as documentNumber,\n" +
            "\t    CONCAT(u.FIRST_NAME, ', ',u.LAST_NAME) AS fullName,\n" +
            "\t   \tCONCAT(em.BRAND, ', ', em.MODEL, ', ', em.SERIAL_NUMBER) AS electricalMeter,\n" +
            "\t   \tCONCAT(a.STREET_NAME,  ', ', a.STREET_NUMBER,  ', ', a.ZIP_CODE,  ', ', a.CITY ) AS address,\n" +
            "\t   \tMIN(m.MEASUREMENT_DATE) AS initialDate,\n" +
            "\t   \tMAX(m.MEASUREMENT_DATE) AS finalDate,\n" +
            "\t   \n" +
            "\t   \tROUND(MAX(m.MEASUREMENT_KWH) - MIN(m.MEASUREMENT_KWH), 2)  AS totalEnergyConsumption, \n" +
            "\t   \tROUND( r.PRICE_KWH * (MAX(m.MEASUREMENT_KWH) - MIN(m.MEASUREMENT_KWH)) , 2) AS totalAmount \n" +
            "    FROM USERS u\n" +
            "    INNER JOIN ELECTRICAL_METERS em ON u.ID = em.USER_ID\n" +
            "   INNER JOIN ADDRESSES a ON em.ADDRESS_ID = a.ID\n" +
            "    INNER JOIN RATES r ON em.RATE_ID = r.ID\n" +
            "    INNER JOIN ELECTRICAL_MEASUREMENTS m ON em.ID = m.ELECTRICAL_METER_ID\n" +
            "    WHERE u.IS_ENABLED = true AND u.DOCUMENT_NUMBER = :documentNumber \n" +
            "AND m.MEASUREMENT_DATE BETWEEN :from AND :to ;", nativeQuery = true)
    Optional<EnergyConsumptionView> findEnergyConsumptionByDocumentNumberAndDate(@Param("documentNumber")String documentNumber,
                                                                                 @Param("from") LocalDateTime from,
                                                                                 @Param("to") LocalDateTime to);

    @Query(value = "SELECT \n" +
            "\n" +
            "    u.DOCUMENT_NUMBER as documentNumber,\n" +
            "    CONCAT(u.FIRST_NAME, ', ',u.LAST_NAME) AS fullName,\n" +
            "    CONCAT(em.BRAND, ', ', em.MODEL, ', ', em.SERIAL_NUMBER) AS electricalMeter,\n" +
            "    CONCAT(a.STREET_NAME,  ', ', a.STREET_NUMBER,  ', ', a.ZIP_CODE,  ', ', a.CITY ) AS address,\n" +
            "    m.MEASUREMENT_DATE AS measurementDate,\n" +
            "   m.MEASUREMENT_KWH AS measurement\n" +
            "  \n" +
            "    FROM USERS u\n" +
            "            INNER JOIN ELECTRICAL_METERS em ON u.ID = em.USER_ID\n" +
            "            INNER JOIN ADDRESSES a ON em.ADDRESS_ID = a.ID\n" +
            "            INNER JOIN RATES r ON em.RATE_ID = r.ID\n" +
            "            INNER JOIN ELECTRICAL_MEASUREMENTS m ON m.ELECTRICAL_METER_ID = em.ID \n" +
            "            WHERE u.IS_ENABLED = true AND u.DOCUMENT_NUMBER = :documentNumber \n" +
            "            AND m.MEASUREMENT_DATE BETWEEN :from  AND :to \n" +
            "            ORDER BY m.MEASUREMENT_DATE ASC;", nativeQuery = true)
    List<MeasurementView> findMeasurementByDocumentNumberAndDate(@Param("documentNumber")String documentNumber,
                                                                 @Param("from") LocalDateTime from,
                                                                 @Param("to") LocalDateTime to);


    @Query(value = " SELECT \n" +
            "\n" +
            "    u.DOCUMENT_NUMBER as documentNumber,\n" +
            "    CONCAT(u.FIRST_NAME, ', ',u.LAST_NAME) AS fullName,\n" +
            "    CONCAT(em.BRAND, ', ', em.MODEL, ', ', em.SERIAL_NUMBER) AS electricalMeter,\n" +
            "    CONCAT(a.STREET_NAME,  ', ', a.STREET_NUMBER,  ', ', a.ZIP_CODE,  ', ', a.CITY ) AS address,\n" +
            "    MIN(m.MEASUREMENT_DATE) AS initialDate,\n" +
            "    MAX(m.MEASUREMENT_DATE) AS finalDate,\n" +
            "    \n" +
            "    ROUND(MAX(m.MEASUREMENT_KWH) - MIN(m.MEASUREMENT_KWH), 2)  AS totalEnergyConsumption,\n" +
            "    ROUND( r.PRICE_KWH * (MAX(m.MEASUREMENT_KWH) - MIN(m.MEASUREMENT_KWH)) , 2) AS totalAmount \n" +
            "    FROM USERS u\n" +
            "    INNER JOIN ELECTRICAL_METERS em ON u.ID = em.USER_ID\n" +
            "    INNER JOIN ADDRESSES a ON em.ADDRESS_ID = a.ID\n" +
            "    INNER JOIN RATES r ON em.RATE_ID = r.ID\n" +
            "    INNER JOIN ELECTRICAL_MEASUREMENTS m ON em.ID = m.ELECTRICAL_METER_ID\n" +
            "    WHERE u.IS_ENABLED = true AND m.MEASUREMENT_DATE BETWEEN :from AND :to\n" +
            "GROUP BY u.DOCUMENT_NUMBER \n" +
            "ORDER BY ROUND(MAX(m.MEASUREMENT_KWH) - MIN(m.MEASUREMENT_KWH), 2) DESC\n" +
            "LIMIT 10 \n" , nativeQuery = true)
    List<EnergyConsumptionView> findEnergyConsumptionTop10(@Param("from") LocalDateTime from,
                                                           @Param("to") LocalDateTime to);


}
