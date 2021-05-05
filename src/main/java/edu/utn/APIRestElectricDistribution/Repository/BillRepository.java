package edu.utn.APIRestElectricDistribution.Repository;

import edu.utn.APIRestElectricDistribution.Domain.Address;
import edu.utn.APIRestElectricDistribution.Domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    //List<Bill> findBillByDateRange(Date initDate, Date finalDate);
    //List<Bill>findByNotBilled()
}
