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
@Entity(name = "ClientOwner")
public class ClientOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClientOwner;
    private String  firstNameClientOwner;
    private String  lastNameClientOwner;
    private String IDCardNumber; // = DNI
    private Integer idAddress;
}
