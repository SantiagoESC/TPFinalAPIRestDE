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
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ELECTRICAL_MEASURMENTS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricalMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ELECTRICAL_METER_ID", referencedColumnName = "ID")
    private ElectricalMeter electricalMeter;

    @Column(name = "MEASUREMENT_DATE")
    private LocalDateTime measurementDate;

    @Column(name = "MEASUREMENT_KWH")
    private Double measurementKWH;
}
