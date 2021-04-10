package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Get region
    public List<User>GetAll(){
        return this.userRepository.findAll();
    }

    public List<User> GetByUsername(String username){
        return this.userRepository.findByUsernameUser(username);
    }

    public User GetById(Integer id) throws Throwable {
        return this.userRepository.findById(id).orElseThrow(Throwable::new);
    }

    //Update region
    public void Update(User user){
        this.userRepository.save(user);
    }
    public void PostUser(User user){
        this.userRepository.save(user);
    }

    //Delete region
    public void Delete(User user){
        this.userRepository.delete(user);
    }
}
