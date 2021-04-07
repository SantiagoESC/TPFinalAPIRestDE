package edu.utn.APIRestElectricDistribution.Controller;

import edu.utn.APIRestElectricDistribution.Domain.ClientOwner;
import edu.utn.APIRestElectricDistribution.Service.ClientOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/client/")
@RestController
public class ClientController {

    //region Properties
    private final ClientOwnerService clientOwnerService;
    //endregion

    //region Constructor
    @Autowired
    public ClientController(ClientOwnerService clientOwnerService) {
        this.clientOwnerService = clientOwnerService;
    }
    //endregion

    //region GET
    @GetMapping("/")
    //@RequestMapping(path = "/",method = RequestMethod.GET)
    private List<ClientOwner> FindAllOrByName(@RequestParam(value = "name",defaultValue = "*",required = false)  String name){
        return (name.equals("*")) ? this.clientOwnerService.GetAll() : this.clientOwnerService.GetByName(name);
    }

    @GetMapping("/{id}")
    private ClientOwner GetById(@PathVariable("id") Integer id) throws Throwable{
        return  this.clientOwnerService.GetById(id);
    }

    //endregion

    //region POST
    @PutMapping("/{id}")
    private void Update(@PathVariable("id") Integer id,@RequestBody ClientOwner client) throws Throwable {
        ClientOwner client1 = this.clientOwnerService.GetById(id);
        client.setIdClientOwner(client1.getIdClientOwner());
        this.clientOwnerService.Update(client);
    }


    @PostMapping("/")
    private void PostClient(@RequestBody ClientOwner client){
        this.clientOwnerService.PostClient(client);
    }
    //endregion

    //region DELETE

    @DeleteMapping("/{id}")
    private void Delete(@PathVariable("id") Integer id) throws Throwable {
        ClientOwner client = this.clientOwnerService.GetById(id);
        this.clientOwnerService.Delete(client);
    }

    //endregion


}
