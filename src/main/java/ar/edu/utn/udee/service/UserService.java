package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.dto.response.UserResponseDTO;
import ar.edu.utn.udee.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User getUserByDocumentNumber(String documentNumber);
	UserResponseDTO getUserDTOByDocumentNumber(String documentNumber);
	UserResponseDTO saveClient(RegisterClientDTO newClient);
}
