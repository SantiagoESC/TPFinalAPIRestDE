package ar.edu.utn.udee.controller.backoffice.impl;

import ar.edu.utn.udee.controller.backoffice.BackofficeController;
import ar.edu.utn.udee.dto.request.RegisterUserDTO;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backoffice/clients")
public class BackofficeControllerImpl implements BackofficeController {

    private final UserService userService;

    @Autowired
    public BackofficeControllerImpl(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> registerClient(@RequestBody RegisterUserDTO client) {
        /*User user = this.userService.registerClient(newClient);
        return ResponseEntity.created(UriUtils.getLocation(user.getIdUser())).build();*/
        return ResponseEntity.ok(User.builder().firstName("Pepe").build());
    }

    /*
    @GetMapping("/{idNumber}")
    public ResponseEntity<User> getClientByIdNumber(@PathVariable(value = "idNumber") String idNumber) throws ValidationException {
        User client = userController.getClientByIDCardNumber(idNumber);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> clients = userController.getAllClients();
        return (clients.size() != 0) ? ResponseEntity.ok(clients) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{IDCardNumber}")
    public ResponseEntity<User> updateUserByIDCardNumber(@PathVariable(value = "IDCardNumber") String IDCardNumber , @RequestBody ClientRequestDTO newClient) throws ValidationException {
        userController.updateClient(IDCardNumber, newClient);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{IDCardNumber}")
    public ResponseEntity deleteClient(@PathVariable(value = "IDCardNumber") String IDCardNumber ) throws ValidationException {

        userController.deleteClient(IDCardNumber);
        return ResponseEntity.ok().build();
    }
    */

}
