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
import id.co.mii.serverapp.models.Parameter;
import id.co.mii.serverapp.services.ParameterService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/parameter")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class ParameterController {

    private ParameterService ParameterService;

    // @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Parameter> getAll() {
        return ParameterService.getAll();
    }

    // @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Parameter getById(@PathVariable Long id) {
        return ParameterService.getById(id);
    }

    // @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Parameter create(@RequestBody Parameter parameter) {
        return ParameterService.create(parameter);
    }

    // @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Parameter update(@PathVariable Long id, @RequestBody Parameter parameter) {
        return ParameterService.update(id, parameter);
    }

    // @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Parameter delete(@PathVariable Long id) {
        return ParameterService.delete(id);
    }
}
