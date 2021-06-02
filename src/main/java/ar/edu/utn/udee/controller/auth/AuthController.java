package ar.edu.utn.udee.controller.auth;

import ar.edu.utn.udee.dto.UserDTO;
import ar.edu.utn.udee.dto.request.LoginRequestDTO;
import ar.edu.utn.udee.dto.response.LoginResponseDTO;
import ar.edu.utn.udee.exception.InvalidLoginException;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.service.UserService;
import ar.edu.utn.udee.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws InvalidLoginException {
        User user = this.userService.login(loginRequestDTO.getIdNumber(), loginRequestDTO.getPassword());
        if (user!=null){
            UserDTO dto = this.modelMapper.map(user, UserDTO.class);
            return ResponseEntity.ok(LoginResponseDTO.builder().token(JWTUtils.generateToken(dto)).build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
