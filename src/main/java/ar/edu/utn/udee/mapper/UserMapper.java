package ar.edu.utn.udee.mapper;

import ar.edu.utn.udee.dto.AuthorizationRequest;
import ar.edu.utn.udee.dto.UserResponse;
import ar.edu.utn.udee.models.User;

public class UserMapper {

	private UserMapper() {
		throw new IllegalStateException("Mapper class");
	}

	public static UserResponse toResponse(User user) {
		return UserResponse.builder().id(user.getId()).firstName(user.getFirstName())
				.documentNumber(user.getDocumentNumber()).build();
	}

	public static User toDomain(AuthorizationRequest authorizationRequest) {
		return User.builder().documentNumber(authorizationRequest.getDocumentNumber()).password(authorizationRequest.getPassword())
				.build();
	}
}
