package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Parameter;
import id.co.mii.serverapp.models.Result;
import id.co.mii.serverapp.repository.ParameterRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParameterService {

    private ParameterRepository parameterRepository;

    public List<Parameter> getAll() {
        return parameterRepository.findAll();
    }

    public Parameter getById(Long id) {
        return parameterRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parameter not found !"));
    }

    public Parameter getByNotes(String notes) {
        return parameterRepository
                .findByNotes(notes)
                .orElse(null);
    }

    public Parameter create(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public Parameter update(Long id, Parameter parameter) {
        getById(id);
        parameter.setId(id);
        return parameterRepository.save(parameter);
    }

    public Parameter delete(Long id) {
        Parameter parameter = getById(id);
        parameterRepository.delete(parameter);
        return parameter;
    }
}
