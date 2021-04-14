package edu.utn.APIRestElectricDistribution.Service;

import edu.utn.APIRestElectricDistribution.Domain.ClientOwner;
import edu.utn.APIRestElectricDistribution.Repository.ClientOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientOwnerService {

    private final ClientOwnerRepository clientOwnerRepository;

    @Autowired
    public ClientOwnerService(ClientOwnerRepository clientOwnerRepository) {
        this.clientOwnerRepository = clientOwnerRepository;
    }

    //TODO asdasda

    //region GET
    public List<ClientOwner> GetAll() {
        return clientOwnerRepository.findAll();
    }

    public List<ClientOwner> GetByName(String name) {
        return this.clientOwnerRepository.findByFirstNameClientOwner(name);
    }

    public ClientOwner GetById(Integer id) throws Throwable {
        return this.clientOwnerRepository.findById(id).orElseThrow(Throwable::new);
    }

    //endregion

    //region UPDATE
    public void Update(ClientOwner client) {
        this.clientOwnerRepository.save(client);
    }

    public void PostClient(ClientOwner client) {
        this.clientOwnerRepository.save(client);
    }

    public void Delete(ClientOwner client) {
        this.clientOwnerRepository.delete(client);
    }
    //endregion
}
