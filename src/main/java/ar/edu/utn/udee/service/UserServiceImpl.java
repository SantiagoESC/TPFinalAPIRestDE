package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.RegisterClientDTO;
import ar.edu.utn.udee.exception.AlreadyExistsException;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.mapper.UserDetailsMapper;
import ar.edu.utn.udee.models.Role;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.repository.RoleRepository;
import ar.edu.utn.udee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String documentNumber) throws UsernameNotFoundException {
		final User retrievedUser = this.userRepository.findByDocumentNumber(documentNumber)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid document number or password"));

		return UserDetailsMapper.build(retrievedUser);
	}

	public Role getRoleByName(String name){
		return this.roleRepository.findByRoleName(name)
				.orElseThrow(()-> new NotFoundException("Role not found"));
	}

	@Override
	public User getUser(long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public User getUserByDocumentNumber(String documentNumber) {
		return this.userRepository.findByDocumentNumber(documentNumber)
				.orElseThrow(() -> new NotFoundException("User not found with document number : " + documentNumber));
	}

	@Override
	public User saveClient(RegisterClientDTO newClient) {
		Role userRole = getRoleByName("CLIENT");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);

		User userExist = this.userRepository.findByDocumentNumber(newClient.getDocumentNumber()).orElse(null);

		if(Objects.nonNull(userExist)){
			throw new AlreadyExistsException("User already exist!");
		} else {
			User userToSave = User.builder()
					.firstName(newClient.getFirstName())
					.lastName(newClient.getLastName())
					.documentNumber(newClient.getDocumentNumber())
					.password(newClient.getPassword())
					.roles(roles).build();
			return userRepository.save(userToSave);
		}
	}

}
