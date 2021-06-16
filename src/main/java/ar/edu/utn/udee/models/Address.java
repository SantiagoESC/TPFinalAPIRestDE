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
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;

    @Column(name = "STREET_NUMBER", nullable = false)
    private Integer streetNumber;

    @Column(name = "FLOOR")
    private String floor;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "CITY")
    private String city;
}
