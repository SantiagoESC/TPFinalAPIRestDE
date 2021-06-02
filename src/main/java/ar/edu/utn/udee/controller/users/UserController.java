package ar.edu.utn.udee.controller.users;

import ar.edu.utn.udee.dto.UserDTO;
import ar.edu.utn.udee.dto.request.RegisterUserDTO;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.service.UserService;
import ar.edu.utn.udee.utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterUserDTO userDto) {
        User user = this.userService.registerUser(userDto);
        return ResponseEntity.created(UriUtils.getLocation(user.getId())).build();
    }

    @GetMapping(value = "/userDetails")
    public ResponseEntity<UserDTO> userDetails(Authentication auth) {
        return ResponseEntity.ok((UserDTO)auth.getPrincipal());
    }

}
