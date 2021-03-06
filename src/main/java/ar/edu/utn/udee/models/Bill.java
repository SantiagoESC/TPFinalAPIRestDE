package ar.edu.utn.udee.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "BILLS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE_BILL")
    private LocalDateTime dateBill;

    @Column(name = "EXPIRATION_DATE")
    private LocalDateTime expirationDate;

    @Column(name = "IS_PAID")
    private boolean isPaid;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "ELECTRICAL_METER_ID", referencedColumnName = "ID", nullable = false)
    private ElectricalMeter electricalMeter;

    @OneToOne
    @JoinColumn(name = "ELECTRICAL_MEASUREMENT_INITIAL_ID",
            referencedColumnName = "ID")
    private ElectricalMeasurement electricalMeasurementInitialId;

    @OneToOne
    @JoinColumn(name = "ELECTRICAL_MEASUREMENT_FINAL_ID",
            referencedColumnName = "ID")
    private ElectricalMeasurement electricalMeasurementFinalId;

}
