 package edu.utn.APIRestElectricDistribution.Domain;

 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.sun.istack.NotNull;
 import edu.utn.APIRestElectricDistribution.Domain.Enums.Role;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import javax.persistence.*;

 @NoArgsConstructor
 @AllArgsConstructor
 @Data
 @Builder
 @Entity
 public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_user")
     private Long idUser;

     @NotNull
     @Column(name = "first_name")
     private String firstName;

     @NotNull
     @Column(name = "last_name")
     private String lastName;

     @NotNull
     @Column(name = "ID_card_number")
     private String IDCardNumber;

     @NotNull
     @Column(name = "password")
     private String password;

     @JsonIgnore
     @Column(name = "enabled")
     private Boolean enabled;

     @Enumerated(EnumType.STRING)
     @Column(name = "user_role")
     private Role role;

     @NotNull
     //TENDRIA QUE SER ONETOMANY----------------------------------------------------------------------------------------
     @OneToOne
     @JoinColumn(name = "ELECTRICAL_METER_ID", referencedColumnName = "ID_ELECTRICAL_METER")
     private ElectricalMeter electricalMeterId;
 }
