package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.Client;
import edu.utn.APIRestElectricDistribution.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/client")
@RestController
public class ClientController {
    //region Properties
    private final ClientService clientService;
    //endregion

    //region Constructor
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    //endregion

    //region GET
    @GetMapping("/")
    //@RequestMapping(path = "/",method = RequestMethod.GET)
    private List<Client> FindAll(@RequestParam(value = "name",defaultValue = "*",required = false)  String name){
        return (name.equals("*")) ? this.clientService.GetAll() : this.clientService.GetByName(name);
    }
    //endregion

    //region POST

    //endregion
}
