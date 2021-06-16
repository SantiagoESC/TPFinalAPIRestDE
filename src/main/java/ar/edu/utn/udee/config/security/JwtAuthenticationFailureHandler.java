package ar.edu.utn.udee.config.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final int STATUS_UNAUTHORIZED_VALUE = HttpStatus.UNAUTHORIZED.value();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {
		response.setStatus(STATUS_UNAUTHORIZED_VALUE);
		response.setContentType("application/json");
		response.getWriter().append(jsonResponse());
	}

	private String jsonResponse() {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", "
				+ "\"status\": " + STATUS_UNAUTHORIZED_VALUE + ", "
				+ "\"error\": \"Unauthorized\", "
				+ "\"message\": \"Authentication failed: bad credentials\", "
				+ "\"path\": \"/login\"}";
	}
}
