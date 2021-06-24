package ar.edu.utn.udee.controller;

import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.dto.response.UserResponseDTO;
import ar.edu.utn.udee.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

	private static final long USER_ID = 1L;
	public static final String USER_NAME = "USER_NAME";
	private static final String USER_FULL_NAME = USER_NAME + " " + USER_NAME;
	private static final String USER_DOCUMENT_NUMBER = "123456789";
	public static final String USER_PASSWORD = "Password1!";
	private static final String ENCRYPTED_PASSWORD = "ENCRYPTED_PASSWORD";

	@Mock
	private UserServiceImpl userService;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserController userController;


	@Test
	void whenBackOfficeAddClientThenClientIsCreated(){

		RegisterClientDTO clientToSave = RegisterClientDTO.builder().firstName(USER_NAME).lastName(USER_NAME)
				.documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASSWORD)
				.build();
		UserResponseDTO clientSaved = UserResponseDTO.builder()
				.fullName(USER_FULL_NAME)
				.documentNumber(USER_DOCUMENT_NUMBER).build();

		when(this.passwordEncoder.encode(USER_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
		when(userService.saveClient(clientToSave)).thenReturn(clientSaved);

		ResponseEntity<UserResponseDTO> response = this.userController.saveClient(clientToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}
