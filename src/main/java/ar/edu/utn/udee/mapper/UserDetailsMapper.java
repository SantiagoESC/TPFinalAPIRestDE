package ar.edu.utn.udee.mapper;

import ar.edu.utn.udee.models.Role;
import ar.edu.utn.udee.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

	private UserDetailsMapper() {
		throw new IllegalStateException("Mapper class");
	}

	public static UserDetails build(User user) {
		return new org.springframework.security.core.userdetails.User(user.getDocumentNumber(), user.getPassword(), getAuthorities(user));
	}

	private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
		Set<Role> roles = retrievedUser.getRoles();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))); // NOSONAR

		return authorities;
	}
}
