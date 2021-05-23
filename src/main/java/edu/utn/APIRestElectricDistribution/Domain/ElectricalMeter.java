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
    @Column(name = "id_electrical_meter")
    private Integer idElectricalMeter;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User userId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    private Address addressId;

    @NotNull
    @Column(name = "serial_number")
    private Integer serialNumber;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "model")
    private String model;
}
