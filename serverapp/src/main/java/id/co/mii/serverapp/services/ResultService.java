package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Result;
import id.co.mii.serverapp.repository.ResultRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResultService {
    private ResultRepository resultRepository;

    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    public Result getById(Long id) {
        return resultRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found !"));
    }

    public Result create(Result result) {
        return resultRepository.save(result);
    }

    public Result update(long id, Result result) {
        getById(id);
        result.setId(id);
        return resultRepository.save(result);
    }

    public Result delete(long id) {
        Result result = getById(id);
        resultRepository.delete(result);
        return result;
    }

}
