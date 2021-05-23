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
    @Column(name = "id_address")
    private Integer idAddress;

    @NotNull
    @Column(name = "street_name")
    private String streetName;

    @NotNull
    @Column(name = "street_number")
    private Integer streetNumber;

    //Optional -----------------
    @Column(name = "floor")
    private String floor;

    @Column(name = "department")
    private String department;
    //--------------------------
    @NotNull
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Column(name = "city")
    private String city;

}
