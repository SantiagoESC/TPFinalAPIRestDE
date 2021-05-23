package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Dto.ClientRequestDTO;
import edu.utn.APIRestElectricDistribution.Exceptions.InvalidLoginException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserAlreadyExistsException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserNotFoundException;
import edu.utn.APIRestElectricDistribution.Exceptions.ValidationException;
import edu.utn.APIRestElectricDistribution.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;


import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

    //region Properties
    private final UserService userService;
    //endregion

    //region Constructor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //endregion

    //region login
    public User login(String idCardNumber, String password) throws UserNotFoundException, ValidationException, InvalidLoginException, NoSuchAlgorithmException {
        if ((idCardNumber != null) && (!idCardNumber.isEmpty()) && (password != null) && !password.isEmpty()){
            return userService.login(idCardNumber, password);
        }else{
            throw new ValidationException("IDCardNumber and password must have a value");
        }
    }
    //endregion

    //region add Client
    public User addClient (ClientRequestDTO newClient) throws UserAlreadyExistsException, ValidationException, NoSuchAlgorithmException {
        if(!newClient.isValid()) throw new ValidationException("Error - does not include all necessary information ");
        return userService.addClient(newClient);
    }
    //endregion

    //region getClient by id card
    public User getClientByIDCardNumber(String IDCardNumber) throws UserNotFoundException, ValidationException {
        if ((IDCardNumber != null) &&(!IDCardNumber.isEmpty()) ) {
            return userService.getClientByIDCardNumber(IDCardNumber);
        } else {
            throw new ValidationException("IDCardNumber must have a value");
        }
    }
    //endregion

    //region get all clients
    public List<User> getAllClients()throws JpaSystemException {
        return userService.getAllClients();
    }
    //endregion

    //region update
    public User updateClient(String IDCardNumber, ClientRequestDTO newClient) throws UserNotFoundException, ValidationException {
        if ((IDCardNumber != null) &&(!IDCardNumber.isEmpty()) ) {
            return userService.updateClient(IDCardNumber, newClient);
        } else {
            throw new ValidationException("IDCardNumber must have a value");
        }
    }
    //endregion

    //region delete
    public void deleteClient(String  IDCardNumber) throws ValidationException, UserNotFoundException {
        if ((IDCardNumber != null) &&(!IDCardNumber.isEmpty()) ) {
            userService.deleteClient(IDCardNumber);
        } else {
            throw new ValidationException("IDCardNumber must have a value");
        }
    }
    //endregion

}
