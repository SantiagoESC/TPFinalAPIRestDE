package ar.edu.utn.udee.controller;

import ar.edu.utn.udee.dto.AuthorizationRequest;
import ar.edu.utn.udee.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthorizationTest {

	private static final String DNI_1 = "123456789";
	private static final String DNI_2 = "12345678";
	private static final String DNI_3 = "987654321";

	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldAuthorizeUser() throws Exception {
		AuthorizationRequest request = AuthorizationRequest.builder().documentNumber(DNI_1).password("password1")
				.build();
		final MvcResult mvcResult = mockMvc.perform(
				post("/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		assertThat(mvcResult.getResponse().getHeader(Constants.HEADER_AUTHORIZATION_KEY)).isNotBlank();
	}

	@Test
	void shouldNotAuthorizeUser() throws Exception {
		AuthorizationRequest request = AuthorizationRequest.builder().documentNumber(DNI_1).password("password2")
				.build();
		mockMvc.perform(
				post("/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isUnauthorized());
	}

	@Test
	void shouldAuthorizeAdminUserToGetUserInfo() throws Exception {
		String token = getToken(DNI_1, "password1");

		mockMvc.perform(
				get("/users/profile").header(Constants.HEADER_AUTHORIZATION_KEY, token))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	private String getToken(String userTest, String password1) throws Exception {
		AuthorizationRequest request = AuthorizationRequest.builder().documentNumber(userTest).password(password1)
				.build();
		final MvcResult mvcResult = mockMvc.perform(
				post("/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		return mvcResult.getResponse().getHeader(Constants.HEADER_AUTHORIZATION_KEY);
	}

	@Test
	void shouldAuthorizeUserToGetUserInfo() throws Exception {
		String token = getToken(DNI_2, "password2");

		mockMvc.perform(
				get("/users/profile").header(Constants.HEADER_AUTHORIZATION_KEY, token))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	void shouldNotAuthorizeOperationalUserToGetUserInfo() throws Exception {
		String token = getToken(DNI_3, "password2");

		mockMvc.perform(
				get("/users/profile").header(Constants.HEADER_AUTHORIZATION_KEY, token))
				.andDo(print()).andExpect(status().isForbidden()).andReturn();
	}
/*
	@Test
	void shouldAuthorizeAdminUserToSave() throws Exception {
		String token = getToken(DNI_1, "password1");

		AuthorizationRequest request = AuthorizationRequest.builder().documentNumber("111222333").password("password4")
				.build();
		mockMvc.perform(
				post("/users").header(Constants.HEADER_AUTHORIZATION_KEY, token).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void shouldNotAuthorizeOperationalUserToSave() throws Exception {
		String token = getToken(DNI_3, "password2");

		AuthorizationRequest request = AuthorizationRequest.builder().documentNumber("11223344").password("password4")
				.build();
		mockMvc.perform(
				post("/users").header(Constants.HEADER_AUTHORIZATION_KEY, token).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isForbidden()).andReturn();
	}
*/
}
