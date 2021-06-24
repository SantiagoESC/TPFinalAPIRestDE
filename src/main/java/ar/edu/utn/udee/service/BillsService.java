package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.response.BillsResponseDTO;

import java.util.List;

public interface BillsService {

    List<BillsResponseDTO> getBillsByDocumentNumber(String documentNumber, Boolean isPaid);
}
