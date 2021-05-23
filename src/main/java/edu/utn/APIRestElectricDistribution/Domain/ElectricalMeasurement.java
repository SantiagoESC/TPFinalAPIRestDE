package edu.utn.APIRestElectricDistribution.Domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class ElectricalMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_electrical_measurement")
    private Integer idElectricalMeasurement;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_electrical_meter", referencedColumnName = "id_electrical_meter")
    private ElectricalMeter electricalMeterId;

    @NotNull
    @Column(name = "measurement_date")
    private LocalDateTime measurementDate;

    @NotNull
    @Column(name = "measurement_kwh")
    private float measurementKWH;


}
