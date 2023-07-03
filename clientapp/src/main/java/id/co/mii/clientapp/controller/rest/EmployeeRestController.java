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

import id.co.mii.clientapp.model.Employee;
import id.co.mii.clientapp.service.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeRestController {

    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }

}
