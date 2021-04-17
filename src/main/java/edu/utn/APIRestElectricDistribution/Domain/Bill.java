package edu.utn.APIRestElectricDistribution.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
//@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BILL")
    private Integer idBill;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ELECTRICAL_METER_ID", referencedColumnName = "ID_ELECTRICAL_METER")
    private ElectricalMeter electricMeterId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "ELECTRICAL_MEASUREMENT_INITIAL_ID",
                referencedColumnName = "ID_ELECTRICAL_MEASUREMENT")
    private ElectricalMeasurement electricalMeasurement_Initial_Id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "ELECTRICAL_MEASUREMENT_FINAL_ID",
                referencedColumnName = "ID_ELECTRICAL_MEASUREMENT")
    private ElectricalMeasurement electricalMeasurement_Final_Id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "RATE_ID", referencedColumnName = "ID_RATE")
    private Rate rateId;

    @NotNull
    @Column(name = "DATE_BILL")
    private Date dateBill;

    @NotNull
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @NotNull
    @Column(name = "BILLED")
    private boolean billed;

    @NotNull
    @Column(name = "TOTAL_PRICE")
    private float totalPrice;
}
