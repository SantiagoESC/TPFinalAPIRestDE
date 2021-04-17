package edu.utn.APIRestElectricDistribution.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
//@Table(name = "electrical_measurements")
public class ElectricalMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ELECTRICAL_MEASUREMENT")
    private Integer idElectricalMeasurement;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ELECTRICAL_METER_ID", referencedColumnName = "ID_ELECTRICAL_METER")
    private ElectricalMeter electricalMeterId;

    @NotNull
    @Column(name = "MEASUREMENT_DATE")
    private LocalDateTime measurementDate;

    @NotNull
    @Column(name = "MEASUREMENT_KWH")
    private float measurementKWH;


}
