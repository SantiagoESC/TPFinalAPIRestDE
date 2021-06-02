package ar.edu.utn.udee.service;

import ar.edu.utn.udee.dto.request.RegisterUserDTO;
import ar.edu.utn.udee.exception.AlreadyExistsException;
import ar.edu.utn.udee.exception.InvalidLoginException;
import ar.edu.utn.udee.exception.NotFoundException;
import ar.edu.utn.udee.models.User;
import ar.edu.utn.udee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByIdNumber(String idNumber){
        return this.userRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new NotFoundException("User Not Found with Id Number" + idNumber));
    }

    public User login(String idNumber, String password) throws InvalidLoginException {

        return this.userRepository.findByIdNumberAndPassword(idNumber, password)
                .orElseThrow(()-> new InvalidLoginException("IdNumber and/or password are incorrect"));
        /*User user = this.findByIdNumber(idNumber);
        if(this.passwordEncoder.matches(user.getPassword(), password)){
            return user;
        }else{
            throw new InvalidLoginException("IdNumber and/or password are incorrect");
        }*/
    }

    public User registerUser(RegisterUserDTO userDto){
        User user = this.userRepository.findByIdNumber(userDto.getIdNumber()).orElse(null);
        if(Objects.nonNull(user)) {
            throw new AlreadyExistsException("User already exist!");
        }
        else{
            user = User.builder()
                    .firstName(userDto.getFirstName()).lastName(userDto.getLastName())
                    .idNumber(userDto.getIdNumber()).password(this.passwordEncoder.encode(userDto.getPassword()))
                    .role(userDto.getRole()).isEnabled(Boolean.TRUE).build();
        }
        return this.userRepository.save(user);
    }

    //TODO Add New Client and Electrical Meter
    /*public User registerClient (UserRegistrationDTO newClient) throws AlreadyExistsException {

        //insertar en la tabal users (client), medidor, address

        return new User();
    }*/



}
