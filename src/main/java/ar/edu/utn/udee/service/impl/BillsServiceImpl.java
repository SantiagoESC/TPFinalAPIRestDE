package ar.edu.utn.udee.service.impl;

import ar.edu.utn.udee.dto.response.BillsResponseDTO;
import ar.edu.utn.udee.repository.BillRepository;
import ar.edu.utn.udee.service.BillsService;
import ar.edu.utn.udee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillsServiceImpl implements BillsService {

    private final UserService userService;
    private final BillRepository billRepository;

    @Autowired
    public BillsServiceImpl(UserService userService, BillRepository billRepository) {
        this.userService = userService;
        this.billRepository = billRepository;
    }

    @Override
    public List<BillsResponseDTO> getBillsByDocumentNumber(String documentNumber, Boolean isPaid){

        return this.billRepository.findByDocumentNumber(documentNumber, isPaid);
    }

}
