package edu.utn.APIRestElectricDistribution.Controller.Backoffice;

import edu.utn.APIRestElectricDistribution.Controller.UserController;
import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Dto.ClientRequestDTO;
import edu.utn.APIRestElectricDistribution.Exceptions.UserAlreadyExistsException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserNotFoundException;
import edu.utn.APIRestElectricDistribution.Exceptions.ValidationException;
import edu.utn.APIRestElectricDistribution.Utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/backoffice/clients")
public class ClientBackofficeController {

    //region properties
    private final UserController userController;
    //endregion

    //region constructor
    @Autowired
    public ClientBackofficeController(UserController userController) {
        this.userController = userController;
    }
    //endregion

    //region post
    @PostMapping
    public ResponseEntity<User> addNewClient(@RequestBody ClientRequestDTO newClient) throws UserAlreadyExistsException, ValidationException, NoSuchAlgorithmException{
        User user = userController.addClient(newClient);
        return ResponseEntity.created(UriUtils.getLocation(user.getIdUser())).build();
    }
    //endregion

    //region get
    @GetMapping("/{IDCardNumber}")
    public ResponseEntity<User> getClientByIDCardNumber(@PathVariable(value = "IDCardNumber") String IDCardNumber) throws ValidationException, UserNotFoundException {

        User client = userController.getClientByIDCardNumber(IDCardNumber);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> clients = userController.getAllClients();
        return (clients.size() != 0) ? ResponseEntity.ok(clients) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //endregion

    //region put
    @PutMapping("/{IDCardNumber}")
    public ResponseEntity<User> updateUserByIDCardNumber(@PathVariable(value = "IDCardNumber") String IDCardNumber , @RequestBody ClientRequestDTO newClient) throws UserNotFoundException, ValidationException {
        userController.updateClient(IDCardNumber, newClient);
        return ResponseEntity.accepted().build();
    }
    //endregion

    //region delete
    @DeleteMapping("/{IDCardNumber}")
    public ResponseEntity deleteClient(@PathVariable(value = "IDCardNumber") String IDCardNumber ) throws ValidationException, UserNotFoundException {

        userController.deleteClient(IDCardNumber);
        return ResponseEntity.ok().build();
    }
    //endregion
}
