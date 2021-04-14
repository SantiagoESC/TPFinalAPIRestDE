package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    //region Get

    @GetMapping("/")
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }



    @GetMapping("/{id}")
    public User GetById(@PathVariable("id")Integer id) throws Throwable{
        return this.userService.GetById(id);
    }
    //endregion

    //region Post
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
    //endregion

    //region Delete
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable("id")Integer id) throws Throwable{
        User user = this.userService.GetById(id);
        this.userService.Delete(user);
    }
    //endregion

}
