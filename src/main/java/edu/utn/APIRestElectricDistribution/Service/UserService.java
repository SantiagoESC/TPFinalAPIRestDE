package edu.utn.APIRestElectricDistribution.Service;


import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    //region Properties
    private final UserRepository userRepository;
    //endregion

    //region Constructor
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //endregion

    //region Get
    public List<User>getAll(){
        return this.userRepository.findAll();
    }

    public List<User> GetByUsername(String username){
        return new ArrayList<User>();//this.userRepository.findByUsernameUser(username);
    }

    public User GetById(Integer id) throws Throwable {
        return this.userRepository.findById(id).orElseThrow(Throwable::new);
    }
    //endregion

    //region Update
    public void Update(User user){
        this.userRepository.save(user);
    }
    public void PostUser(User user){
        this.userRepository.save(user);
    }
    //endregion

    //region Delete
    public void Delete(User user){
        this.userRepository.delete(user);
    }


    //endregion
}
