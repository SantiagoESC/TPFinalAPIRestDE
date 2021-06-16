package ar.edu.utn.udee.config.security;

import ar.edu.utn.udee.dto.AuthorizationRequest;
import ar.edu.utn.udee.utils.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static ar.edu.utn.udee.utils.Constants.HEADER_AUTHORIZATION_KEY;
import static ar.edu.utn.udee.utils.Constants.TOKEN_BEARER_PREFIX;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	@Autowired
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getInputStream(), AuthorizationRequest.class);

			return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getDocumentNumber(), userCredentials.getPassword()));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException {
		String token = TokenProvider.generateToken(authResult);
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
		response.setContentType("application/json");
		response.getWriter().append(jsonResponse(TOKEN_BEARER_PREFIX + " " + token));
	}

	private String jsonResponse(String token) {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", "
				+ "\"token\": \"" + token +"\", "
				+ "\"message\": \"Authentication successfully\", "
				+ "\"path\": \"/login\"}";
	}

}
