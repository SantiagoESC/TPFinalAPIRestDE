package ar.edu.utn.udee.service;

import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.Role;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.repository.RoleRepository;
import ar.edu.utn.udee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

	private static final long USER_ID = 1L;
	private static final String USER_NAME = "Jorge";
	private static final String USER_DOCUMENT_NUMBER = "123456789";
	private static final String USER_PASS = "USER_PASS";

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void loadUserByUsernameShouldMapUserDetails() {
		Set<Role> roles = new HashSet<>();
		roles.add(Role.builder().roleName("ROLE_BACKOFFICE").id(1L).description("ROLE_DESC_BACKOFFICE").build());
		roles.add(Role.builder().roleName("ROLE_CLIENT").id(1L).description("ROLE_DESC_CLIENT").build());

		User expectedUser = User.builder().id(USER_ID).documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASS).roles(roles).build();

		when(userRepository.findByDocumentNumber(USER_DOCUMENT_NUMBER)).thenReturn(Optional.of(expectedUser));

		final UserDetails userDetails = userService.loadUserByUsername(USER_DOCUMENT_NUMBER);

		assertThat(userDetails).isNotNull();
		assertThat(userDetails.getUsername()).isEqualTo(USER_DOCUMENT_NUMBER);
		assertThat(userDetails.getAuthorities()).isNotEmpty();
	}

	@Test
	void loadUserByUsernameShouldThrowExceptionIfUserIsNotFound() {

		Exception exception = assertThrows(UsernameNotFoundException.class, () ->
			userService.loadUserByUsername(USER_DOCUMENT_NUMBER)
		);

		String expectedMessage = "Invalid document number or password";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void getUserByDocumentNumberShouldCallRepository() {

		User expectedUser = User.builder().id(USER_ID).documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASS).build();
		when(userRepository.findByDocumentNumber(USER_DOCUMENT_NUMBER)).thenReturn(Optional.of(expectedUser));
		User user = userService.getUserByDocumentNumber(USER_DOCUMENT_NUMBER);
		assertEquals(expectedUser, user);
	}

	@Test
	void getUserByDocumentNumberShouldThrowExceptionIfUserIsNotFound() {

		Exception exception = assertThrows(NotFoundException.class, () ->
				userService.getUserByDocumentNumber(USER_DOCUMENT_NUMBER)
		);

		String expectedMessage = "User not found with document number : " + USER_DOCUMENT_NUMBER;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}


/*
	@Test
	void saveUserShouldSaveUserWithUserRole() {
		final User userToSave = User.builder().documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASS).build();
		final Role userRole = Role.builder().roleName("ROLE_CLIENT").id(1L).description("ROLE_CLIENT").build();

		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		User encryptedUser = User.builder().documentNumber(USER_DOCUMENT_NUMBER).password(USER_PASS).roles(roles).build();

		when(roleRepository.findByRoleName("CLIENT")).thenReturn(Optional.of(userRole));
		when(userRepository.save(encryptedUser)).thenReturn(encryptedUser);

		final User savedUser = userService.save(userToSave);

		assertThat(savedUser).isEqualTo(encryptedUser);
	}*/

}
