package ar.edu.utn.udee.controller;

import ar.edu.utn.udee.config.security.CurrentUser;
import ar.edu.utn.udee.dto.UserResponse;
import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.mapper.UserMapper;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserServiceImpl userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PreAuthorize("hasRole('ROLE_CLIENT') OR hasRole('ROLE_BACK_OFFICE')") // NOSONAR
	@GetMapping("/profile")
	public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser @Parameter(hidden = true) UserDetails currentUser) {

		final User user = userService.getUserByDocumentNumber(currentUser.getUsername());

		UserResponse userResponse = UserMapper.toResponse(user);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BACKOFFICE')") // NOSONAR
	@PostMapping("/clients")
	public ResponseEntity<User> saveClient(@RequestBody RegisterClientDTO client) {

		client.setPassword(this.passwordEncoder.encode(client.getPassword()));
		User clientToSave = this.userService.saveClient(client);

		return new ResponseEntity<>(clientToSave, HttpStatus.CREATED);
	}

}
