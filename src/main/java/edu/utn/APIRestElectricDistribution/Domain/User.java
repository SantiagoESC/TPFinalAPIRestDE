 package edu.utn.APIRestElectricDistribution.Domain;

 import com.sun.istack.NotNull;
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
// @Table(name = "users")
 public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "ID_USER")
     private Integer idUser;

     @NotNull
     @Column(name = "FIRST_NAME")
     private String firstName;

     @NotNull
     @Column(name = "LAST_NAME")
     private String lastName;

     @NotNull
     @Column(name = "USERNAME")
     private String username;

     @NotNull
     @Column(name = "PASSWORD")
     private String password;

     @NotNull
     @Column(name = "IDCARD_NUMBER")
     private String IDCardNumber;

     @NotNull
     //TENDRIA QUE SER ONETOMANY----------------------------------------------------------------------------------------
     @OneToOne
     @JoinColumn(name = "ELECTRICAL_METER_ID", referencedColumnName = "ID_ELECTRICAL_METER")
     private ElectricalMeter electricalMeterId;
 }
