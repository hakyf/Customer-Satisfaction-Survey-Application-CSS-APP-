package id.co.mii.serverapp.controllers;
import java.util.List;

// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.mii.serverapp.models.Client;
import id.co.mii.serverapp.services.ClientService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/client")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class ClientController {
private ClientService clientService;
//  @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Client> getAll(){
        return clientService.getAll();
    }
// @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){
        return clientService.getById(id);
    }
// @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }
    
//   @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Client update(@PathVariable Integer id,@RequestBody Client client){
        return clientService.update(id, client);
    }
//  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Client delete(@PathVariable Integer id){
        return clientService.delete(id);
    }
    
    
}
