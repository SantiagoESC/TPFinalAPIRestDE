package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.request.RateDTO;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.Rate;
import ar.edu.utn.udee.repository.RateRepository;
import ar.edu.utn.udee.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Objects;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public RateDTO saveRate(RateDTO newRate){

        Rate rate = this.rateRepository.findByTypeRate(newRate.getTypeRate()).orElse(null);
        if(Objects.nonNull(rate)){
            throw new ValidationException("Rate Already exist!");
        }

        Rate rateSaved = this.rateRepository.save(Rate.builder()
                .typeRate(newRate.getTypeRate())
                .price(newRate.getPrice())
                .build());

        return RateDTO.builder()
                .typeRate(rateSaved.getTypeRate())
                .price(rateSaved.getPrice())
                .build();
    }

    @Override
    public RateDTO updateRate(RateDTO rate){

        Rate oldRate = this.rateRepository.findByTypeRate(rate.getTypeRate())
                .orElseThrow(()-> new NotFoundException("Rate not found!"));

        oldRate.setPrice(rate.getPrice());
        Rate newRate = this.rateRepository.save(oldRate);

        return RateDTO.builder()
                .typeRate(newRate.getTypeRate())
                .price(newRate.getPrice())
                .build();
    }

    @Override
    public void deleteRate(String typeRate){
        Rate oldRate = this.rateRepository.findByTypeRate(typeRate)
                .orElseThrow(()-> new NotFoundException("Rate not found!"));
        this.rateRepository.delete(oldRate);
    }

}
