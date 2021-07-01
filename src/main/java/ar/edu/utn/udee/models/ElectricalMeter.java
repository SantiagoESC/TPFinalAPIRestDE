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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ELECTRICAL_METERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricalMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SERIAL_NUMBER", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "IS_ENABLED", columnDefinition = "boolean default true")
    private Boolean isEnabled;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "RATE_ID", referencedColumnName = "ID")
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "electricalMeter")
    private List<Bill> bills;
}
