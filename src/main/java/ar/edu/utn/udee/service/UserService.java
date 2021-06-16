package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User getUser(long id);

	User getUserByDocumentNumber(String documentNumber);

	User saveClient(RegisterClientDTO newClient);
}
