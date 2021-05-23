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
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    private Integer idBill;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_electrical_meter", referencedColumnName = "id_electrical_meter")
    private ElectricalMeter electricMeterId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "electrical_measurement_initial_id",
                    referencedColumnName = "ID_ELECTRICAL_MEASUREMENT")
    private ElectricalMeasurement electricalMeasurement_Initial_Id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "electrical_measurement_final_id",
                    referencedColumnName = "id_electrical_measurement")
    private ElectricalMeasurement electricalMeasurement_Final_Id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_rate", referencedColumnName = "id_rate")
    private Rate rateId;

    @NotNull
    @Column(name = "date_bill")
    private Date dateBill;

    @NotNull
    @Column(name = "expiration_date")
    private Date expirationDate;

    @NotNull
    @Column(name = "billed")
    private boolean billed;

    @NotNull
    @Column(name = "total_price")
    private float totalPrice;
}
