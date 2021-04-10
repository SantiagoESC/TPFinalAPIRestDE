package edu.utn.APIRestElectricDistribution.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBill;

    private Integer electricMeterId;
    private Integer electricalMeasurement_Initial_Id;
    private Integer electricalMeasurement_Final_Id;
    private Integer rateId;

    private Date dateBill;
    private Date expirationDate;
    private boolean billed;
    private float totalPrice;
}
