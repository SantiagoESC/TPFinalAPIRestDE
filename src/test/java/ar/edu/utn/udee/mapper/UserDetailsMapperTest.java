package ar.edu.utn.udee.mapper;

import ar.edu.utn.udee.models.Role;
import ar.edu.utn.udee.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class UserDetailsMapperTest {

	private static final String USER_DOCUMENT_NUMBER = "123456789";

	private User user;

	@BeforeEach
	void setUp() {
		Set<Role> roles = new HashSet<>();
		roles.add(Role.builder().id(1L).roleName("BACKOFFICE").description("DESC_BACKOFFICE").build());
		roles.add(Role.builder().id(1L).roleName("CLIENT").description("DESC_CLIENT").build());

		user = User.builder().id(1L).documentNumber(USER_DOCUMENT_NUMBER).password("PASSWORD").roles(roles).build();
	}

	@Test
	void buildUserDetailsShouldConvertFromUser() {
		List<String> expectedAuthorities = Arrays.asList("ROLE_BACKOFFICE", "ROLE_CLIENT");
		UserDetails userDetails = UserDetailsMapper.build(user);

		assertThat(userDetails).isNotNull();
		assertThat(userDetails.getUsername()).isEqualTo(user.getDocumentNumber());
		assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
		assertThat(userDetails.getAuthorities()).isNotEmpty();
		assertThat(userDetails.getAuthorities()).hasSize(2);

		final List<String> userAuthorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		assertThat(userAuthorities).containsAll(expectedAuthorities);
	}

}
