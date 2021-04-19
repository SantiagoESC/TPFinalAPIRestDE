package edu.utn.APIRestElectricDistribution.Service;

<<<<<<< HEAD

=======
>>>>>>> origin/Franco
import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> origin/Franco
import java.util.List;

@Service
public class UserService {

<<<<<<< HEAD
    //region Properties
    private final UserRepository userRepository;
    //endregion

    //region Constructor
=======
    //
    private final UserRepository userRepository;

>>>>>>> origin/Franco
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
<<<<<<< HEAD
    //endregion

    //region Get
    public List<User>getAll(){
=======

    //Get region
    public List<User>GetAll(){
>>>>>>> origin/Franco
        return this.userRepository.findAll();
    }

    public List<User> GetByUsername(String username){
<<<<<<< HEAD
        return new ArrayList<User>();//this.userRepository.findByUsernameUser(username);
=======
        return this.userRepository.findByUsernameUser(username);
>>>>>>> origin/Franco
    }

    public User GetById(Integer id) throws Throwable {
        return this.userRepository.findById(id).orElseThrow(Throwable::new);
    }
<<<<<<< HEAD
    //endregion

    //region Update
=======

    //Update region
>>>>>>> origin/Franco
    public void Update(User user){
        this.userRepository.save(user);
    }
    public void PostUser(User user){
        this.userRepository.save(user);
    }
<<<<<<< HEAD
    //endregion

    //region Delete
    public void Delete(User user){
        this.userRepository.delete(user);
    }


    //endregion
=======

    //Delete region
    public void Delete(User user){
        this.userRepository.delete(user);
    }
>>>>>>> origin/Franco
}
