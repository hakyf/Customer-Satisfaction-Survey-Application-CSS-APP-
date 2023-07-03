package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Employee;
import id.co.mii.clientapp.service.EmployeeService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("employee", employeeService.getAll());
        model.addAttribute("isActive", "employee");
        return "employee/index";
    }

    @GetMapping("/create")
    public String createForm(Employee employee, Model model) {
        return "employee/create";
    }

    @PostMapping
    public String create(Employee employee) {
        employeeService.create(employee);
        return "redirect:/employee";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Employee employee) {
        employeeService.update(id, employee);
        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/detail";
    }

}
