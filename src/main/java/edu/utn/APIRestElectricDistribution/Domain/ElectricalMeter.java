package edu.utn.APIRestElectricDistribution.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
//@Table(name = "electrical_meter")
public class ElectricalMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ELECTRICAL_METER")
    private Integer idElectricalMeter;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID_USER")
    private User userId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID_ADDRESS")
    private Address addressId;

    @NotNull
    @Column(name = "SERIAL_NUMBER")
    private Integer serialNumber;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;
}
