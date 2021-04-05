package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.Client;
import edu.utn.APIRestElectricDistribution.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //region GET
    public List<Client> GetAll() {
        return clientRepository.findAll();
    }

    public List<Client> GetByName(String name) {
        return this.clientRepository.findByNameClient(name);
    }

   /* public Client GetById(Integer id) {
        return this.clientRepository.
    }*/
    //endregion
}
