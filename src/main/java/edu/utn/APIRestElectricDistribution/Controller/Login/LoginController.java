package edu.utn.APIRestElectricDistribution.Controller.Login;

import edu.utn.APIRestElectricDistribution.Controller.UserController;
import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Dto.LoginRequestDTO;
import edu.utn.APIRestElectricDistribution.Exceptions.InvalidLoginException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserNotFoundException;
import edu.utn.APIRestElectricDistribution.Exceptions.ValidationException;
import edu.utn.APIRestElectricDistribution.Session.SessionManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class LoginController {

    //region properties
    private final UserController userController;
    private final SessionManager sessionManager;
    //endregion

    //region constructor
    public LoginController(UserController userController, SessionManager sessionManager) {
        this.userController = userController;
        this.sessionManager = sessionManager;
    }
    //endregion

    //region post
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO) throws InvalidLoginException, ValidationException {

        String token = null;
        ResponseEntity responseEntity;

        try {
            User user = userController.login(loginRequestDTO.getIDCardNumber(), loginRequestDTO.getPassword());
            token = sessionManager.createSession(user);
            responseEntity = ResponseEntity.ok().headers(createHeaders(token)).build();
        }catch (UserNotFoundException | NoSuchAlgorithmException e){
            throw new InvalidLoginException(e);
        }
        return responseEntity;
    }

    @PostMapping("/logout")
    private ResponseEntity logout(@RequestHeader("Authorization") String token){
        sessionManager.removeSession(token);

        return ResponseEntity.ok().build();
    }
    //endregion


    private HttpHeaders createHeaders(String token) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);
        return httpHeaders;
    }
}
