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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADDRESS")
    private Integer idAddress;

    @NotNull
    @Column(name = "STREET_NAME")
    private String streetName;

    @NotNull
    @Column(name = "STREET_NUMBER")
    private Integer streetNumber;

    //Optional -----------------
    @Column(name = "FLOOR")
    private String floor;

    @Column(name = "DEPARTMENT")
    private String department;
    //--------------------------
    @NotNull
    @Column(name = "ZIP_CODE")
    private String zipCode;

    @NotNull
    @Column(name = "CITY")
    private String city;

}
