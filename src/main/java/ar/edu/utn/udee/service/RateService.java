package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.RateDTO;

public interface RateService {
    
    RateDTO saveRate(RateDTO newRate);
    RateDTO updateRate(RateDTO rate);
    void deleteRate(String typeRate);
}
