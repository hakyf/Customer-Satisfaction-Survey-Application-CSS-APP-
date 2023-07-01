package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Client;

import id.co.mii.serverapp.repository.ClientRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;
    public List<Client> getAll(){
        return clientRepository.findAll();
    }
    public Client getById(Long id){
        return clientRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found !")
        );
    }
    
    public Client create(Client client){
        return clientRepository.save(client);
    }

    public Client update(long id, Client client){
        getById(id);
        client.setId(id);
        return clientRepository.save(client); 
    }

    public Client delete(long id){
        Client client= getById(id);
        clientRepository.delete(client);
        return client;
    }
    
}
