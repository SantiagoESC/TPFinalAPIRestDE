package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> origin/Franco
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

<<<<<<< HEAD
    //region Properties
    private final UserService userService;
    //endregion

    //region Constructor
=======
    //Properties region
    private final UserService userService;

    //Constructor region
>>>>>>> origin/Franco
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
<<<<<<< HEAD
    //endregion

    //region Get

    @GetMapping("/")
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }



=======

    //Get region
    @GetMapping("/")
    public List<User>findAllOrByUsername(@RequestParam(value = "username", defaultValue = "*", required = false) String username){
        return (username.equals("*")) ? this.userService.GetAll() : this.userService.GetByUsername(username);
    }

>>>>>>> origin/Franco
    @GetMapping("/{id}")
    public User GetById(@PathVariable("id")Integer id) throws Throwable{
        return this.userService.GetById(id);
    }
<<<<<<< HEAD
    //endregion

    //region Post
=======

    //Post region
>>>>>>> origin/Franco
    @PutMapping("/{id}")
    public void Update(@PathVariable("id")Integer id,@RequestBody User user) throws Throwable{
        User user1 = this.userService.GetById(id);
        user.setIdUser(user1.getIdUser());
        this.userService.Update(user);
    }

    @PostMapping("/")
    public void PostUser(@RequestBody User user){
        this.userService.PostUser(user);
    }
<<<<<<< HEAD
    //endregion

    //region Delete
=======

    //Delete region
>>>>>>> origin/Franco
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable("id")Integer id) throws Throwable{
        User user = this.userService.GetById(id);
        this.userService.Delete(user);
    }
<<<<<<< HEAD
    //endregion
=======
>>>>>>> origin/Franco

}
