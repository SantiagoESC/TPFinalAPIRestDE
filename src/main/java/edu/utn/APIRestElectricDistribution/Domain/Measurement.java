package edu.utn.APIRestElectricDistribution.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity(name = "measurement")
public class Measurement {

    private Date dateMeasurement;
    private float measurement;
    private float price;

}
