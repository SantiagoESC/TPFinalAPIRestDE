package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.Client;
import edu.utn.APIRestElectricDistribution.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/client/")
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
    private List<Client> FindAllOrByName(@RequestParam(value = "name",defaultValue = "*",required = false)  String name){
        return (name.equals("*")) ? this.clientService.GetAll() : this.clientService.GetByName(name);
    }
    @GetMapping("/{id}")
    private Client GetById(@PathVariable("id") Integer id) throws Throwable{
        return  this.clientService.GetById(id);
    }
    //endregion

    //region POST
    @PutMapping("/{id}")
    private void Update(@PathVariable("id") Integer id,@RequestBody Client client) throws Throwable {
        Client client1 = this.clientService.GetById(id);
        client.setIdClient(client1.getIdClient());
        this.clientService.Update(client);
    }


    @PostMapping("/")
    private void PostClient(@RequestBody Client client){
        this.clientService.PostClient(client);
    }
    //endregion

    //region DELETE

    @DeleteMapping("/{id}")
    private void Delete(@PathVariable("id") Integer id) throws Throwable {
        Client client = this.clientService.GetById(id);
        this.clientService.Delete(client);
    }

    //endregion


}
