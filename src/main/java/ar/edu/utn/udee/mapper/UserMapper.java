package ar.edu.utn.udee.mapper;

import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.dto.response.UserResponseDTO;
import ar.edu.utn.udee.models.Role;
import ar.edu.utn.udee.models.User;

import java.util.Set;

public class UserMapper {

	private UserMapper() {
		throw new IllegalStateException("Mapper class");
	}

	public static UserResponseDTO toResponse(User user) {
		return UserResponseDTO.builder()
				.fullName(user.getFirstName() + " " + user.getLastName())
				.documentNumber(user.getDocumentNumber()).build();
	}

	public static User clientToEntity(RegisterClientDTO dto, Set<Role> roles){
		return User.builder()
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.documentNumber(dto.getDocumentNumber())
				.password(dto.getPassword())
				.isEnabled(Boolean.TRUE)
				.roles(roles).build();
	}

}
