package ar.edu.utn.udee.config.security;

import ar.edu.utn.udee.service.UserService;
import ar.edu.utn.udee.utils.Constants;
import ar.edu.utn.udee.utils.TokenProvider;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION_KEY);

		if (Strings.isBlank(authorizationHeader) || !authorizationHeader
				.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX + " ", "");

		String documentNumber = TokenProvider.getDocumentNumber(token);
		UserDetails user = this.userService.loadUserByUsername(documentNumber);

		UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, user);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
