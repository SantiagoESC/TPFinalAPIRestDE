package ar.edu.utn.udee.mapper;

import ar.edu.utn.udee.dto.response.UserResponseDTO;
import ar.edu.utn.udee.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class UserMapperTest {

	private static final long USER_ID = 1L;

	private static final String USER_NAME = "USER_NAME";

	@Test
	void toResponseShouldReturnValidUserResponse() {
		User user = User.builder().id(USER_ID).firstName(USER_NAME).password("USER_PASSWORD").build();

		UserResponseDTO userResponseDTO = UserMapper.toResponse(user);

		String fullName = user.getFirstName() + " " + user.getLastName();

		assertEquals(fullName, userResponseDTO.getFullName());
		assertEquals(user.getDocumentNumber(), userResponseDTO.getDocumentNumber());
	}

}
