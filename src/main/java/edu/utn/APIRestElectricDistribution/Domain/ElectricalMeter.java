package edu.utn.APIRestElectricDistribution.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Measurer")
public class ElectricalMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElectricalMeter;

    private Integer idUser;
    private Integer idAddress;

    private Integer serialNumber;
    private String brand;
    private String model;
}
