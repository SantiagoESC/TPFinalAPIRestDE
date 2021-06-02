package ar.edu.utn.udee.models;

import ar.edu.utn.udee.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ID_NUMBER",unique = true, nullable = false)
    private String idNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ENABLED", columnDefinition = "boolean default true")
    private Boolean isEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<ElectricalMeter> electricalMeters;

}
