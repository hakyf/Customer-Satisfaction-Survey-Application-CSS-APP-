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
import id.co.mii.clientapp.model.User;
import id.co.mii.clientapp.service.EmployeeService;
import id.co.mii.clientapp.service.UserService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", userService.getAll());
        model.addAttribute("isActive", "user");
        return "user/index";
    }

    @GetMapping("/create")
    public String createForm(User user, Model model) {
        model.addAttribute("isActive", "user");
        return "user/create";
    }

    @PostMapping
    public String create(User user, Employee employee) {
        userService.create(user);
        employeeService.create(employee);
        return "redirect:/user";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("isActive", "user");
        return "user/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, User user) {
        userService.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("isActive", "user");
        return "user/detail";
    }
}