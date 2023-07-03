package id.co.mii.clientapp.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.clientapp.model.Parameter;
import id.co.mii.clientapp.service.ParameterService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/parameter")
@AllArgsConstructor
public class ParameterRestController {

    private ParameterService parameterService;

    @GetMapping
    public List<Parameter> getAll() {
        return parameterService.getAll();
    }

    @GetMapping("/{id}")
    public Parameter getById(@PathVariable Long id) {
        return parameterService.getById(id);
    }

    @PostMapping
    public Parameter create(@RequestBody Parameter parameter) {
        return parameterService.create(parameter);
    }

    @PutMapping("/{id}")
    public Parameter update(@PathVariable Long id, @RequestBody Parameter parameter) {
        return parameterService.update(id, parameter);
    }

    @DeleteMapping("/{id}")
    public Parameter delete(@PathVariable Long id) {
        return parameterService.delete(id);
    }

}
