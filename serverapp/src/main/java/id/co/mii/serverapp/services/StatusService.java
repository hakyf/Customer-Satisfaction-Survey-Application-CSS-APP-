package id.co.mii.serverapp.services;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Status;

import id.co.mii.serverapp.repository.StatusRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class StatusService {
    private StatusRepository statusRepository;
    public List<Status> getAll(){
        return statusRepository.findAll();
    }
    public Status getById(Long id){
        return statusRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found !")
        );
    }
    
    public Status create(Status status){
        return statusRepository.save(status);
    }

    public Status update(long id, Status status){
        getById(id);
        status.setId(id);
        return statusRepository.save(status); 
    }

    public Status delete(long id){
        Status status= getById(id);
        statusRepository.delete(status);
        return status;
    }
    
}
