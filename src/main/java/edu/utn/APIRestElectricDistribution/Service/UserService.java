package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.User;
import edu.utn.APIRestElectricDistribution.Dto.ClientRequestDTO;
import edu.utn.APIRestElectricDistribution.Exceptions.UserAlreadyExistsException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserNotFoundException;
import edu.utn.APIRestElectricDistribution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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

    //region add client
    public User addClient (ClientRequestDTO newClient) throws UserAlreadyExistsException, NoSuchAlgorithmException {

        User user = userRepository.findByIDCardNumber(newClient.getIDCardNumber());
        if(user != null) throw new UserAlreadyExistsException();

        Long idUser = userRepository.addClient(newClient.getFirstName(), newClient.getLastName(), newClient.getIDCardNumber(), this.hashPassword(newClient.getPassword()));
        return userRepository.getOne(idUser);
    }
    //endregion

    //region get client by id card number
    public User getClientByIDCardNumber(String IDCardNumber) throws UserNotFoundException  {
        User user = userRepository.findByIDCardNumber(IDCardNumber);
        return Optional.ofNullable(user).orElseThrow(UserNotFoundException::new);
    }
    //endregion

    //region get all clients
    public List<User> getAllClients(){
        return userRepository.findAllClients();
    }
    //endregion

    //region login
    public User login(String IDCardNumber, String password) throws UserNotFoundException, NoSuchAlgorithmException {

        User user = userRepository.getUserByIDCardNumberAndPassword(IDCardNumber,  this.hashPassword(password));
        return Optional.ofNullable(user).orElseThrow(UserNotFoundException::new);
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] data = password.getBytes();
        m.update(data, 0, data.length);
        BigInteger i = new BigInteger(1, m.digest());
        return String.format("%1$032X", i);
    }
    //endregion

    //region update
    public User updateClient(String IDCardNumber, ClientRequestDTO newClient) throws UserNotFoundException {

        User existingUser = userRepository.findByIDCardNumber(IDCardNumber);
        Optional.ofNullable(existingUser).orElseThrow(UserNotFoundException::new);

        existingUser.setFirstName( (newClient.getFirstName() != null) ? newClient.getFirstName() : existingUser.getFirstName() );
        existingUser.setLastName( (newClient.getLastName() != null) ? newClient.getLastName() : existingUser.getLastName() );

        Long id = userRepository.updateClient(existingUser.getFirstName(), existingUser.getLastName());
        return userRepository.getOne(id);
    }
    //endregion

    //region delete
    public void deleteClient(String IDCardNumber) throws UserNotFoundException {

        User user = userRepository.findByIDCardNumber(IDCardNumber);
        Optional.ofNullable(user).orElseThrow(UserNotFoundException::new);
        userRepository.removeClientByIDCardNumber(IDCardNumber);
    }
    //endregion

}
