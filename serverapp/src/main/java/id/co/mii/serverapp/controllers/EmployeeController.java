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
import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class EmployeeController {

    private EmployeeService EmployeeService;

    // @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Employee> getAll() {
        return EmployeeService.getAll();
    }

    // @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return EmployeeService.getById(id);
    }

    // @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return EmployeeService.create(employee);
    }

    // @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return EmployeeService.update(id, employee);
    }

    // @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Long id) {
        return EmployeeService.delete(id);
    }
}
