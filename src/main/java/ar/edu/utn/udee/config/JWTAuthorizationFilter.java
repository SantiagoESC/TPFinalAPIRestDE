package ar.edu.utn.udee.config;

import ar.edu.utn.udee.dto.UserDTO;
import ar.edu.utn.udee.utils.JWTUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public JWTAuthorizationFilter() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Internal JWT Filter to check if the request is valid
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain) throws ServletException, IOException {
        try {
            if (containsJWT(request)) {
                Claims claims = validateToken(request);
                if (claims.get("user") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            (response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    /**
     * Method to validate if the token is valid
     */
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(JWTUtils.JWT_HEADER).replace(JWTUtils.JWT_PREFIX, "");
        return Jwts.parser().setSigningKey(JWTUtils.JWT_SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Authentication Method to authorize through Spring
     */
    private void setUpSpringAuthentication(Claims claims) {
        try {
            List<String> authorities = Arrays.asList(claims.get("authorities").toString());
            String userClaim = (String) claims.get("user");
            UserDTO user = objectMapper.readValue(userClaim, UserDTO.class);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (JsonProcessingException e) {
            SecurityContextHolder.clearContext();
        }
    }

    private boolean containsJWT(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(JWTUtils.JWT_HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(JWTUtils.JWT_PREFIX);
    }

}