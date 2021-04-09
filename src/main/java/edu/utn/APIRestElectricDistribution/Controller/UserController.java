package edu.utn.APIRestElectricDistribution.Controller;

//import edu.utn.APIRestElectricDistribution.Domain.User;
//import edu.utn.APIRestElectricDistribution.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/api/user")
//@RestController
//public class UserController {
//
//    //Properties region
//    private final UserService userService;
//
//    //Constructor region
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    //Get region
//    @GetMapping("/")
//    public List<User>findAllOrByUsername(@RequestParam(value = "username", defaultValue = "*", required = false) String username){
//        return (username.equals("*")) ? this.userService.GetAll() : this.userService.GetByUsername(username);
//    }
//
//    @GetMapping("/{id}")
//    public User GetById(@PathVariable("id")Integer id) throws Throwable{
//        return this.userService.GetById(id);
//    }
//
//    //Post region
//    @PutMapping("/{id}")
//    public void Update(@PathVariable("id")Integer id,@RequestBody User user) throws Throwable{
//        User user1 = this.userService.GetById(id);
//        user.setIdUser(user1.getIdUser());
//        this.userService.Update(user);
//    }
//
//    @PostMapping("/")
//    public void PostUser(@RequestBody User user){
//        this.userService.PostUser(user);
//    }
//
//    //Delete region
//    @DeleteMapping("/{id}")
//    public void Delete(@PathVariable("id")Integer id) throws Throwable{
//        User user = this.userService.GetById(id);
//        this.userService.Delete(user);
//    }
//
//}
