package ar.edu.utn.udee.controller;

import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.service.UserServiceImpl;
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
	private static final String USER_DOCUMENT_NUMBER = "123456789";
	public static final String USER_PASSWORD = "USER_PASSWORD";

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
		User clientSaved = User.builder().id(USER_ID).firstName(USER_NAME).lastName(USER_NAME)
				.documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASSWORD).build();

		when(this.passwordEncoder.encode(USER_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
		when(userService.saveClient(clientToSave)).thenReturn(clientSaved);

		ResponseEntity<User> response = this.userController.saveClient(clientToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}




	/*
	@Test
	void saveUserShouldCallService() {
		final AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
				.documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASSWORD).build();
		final User userToSave = User.builder().documentNumber(USER_DOCUMENT_NUMBER).password(ENCRYPTED_PASSWORD).build();
		final User savedUser = User.builder().id(USER_ID).documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASSWORD).build();

		when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
		when(userService.save(userToSave)).thenReturn(savedUser);

		final ResponseEntity<User> userResponse = userController.saveUser(authorizationRequest);

		assertEquals(savedUser, userResponse.getBody());
		assertEquals(HttpStatus.OK, userResponse.getStatusCode());
	}*/

}
