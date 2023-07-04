package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Role;
import id.co.mii.clientapp.service.RoleService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("role", roleService.getAll());
        model.addAttribute("isActive", "role");
        return "role/index";
    }

    @GetMapping("/create")
    public String createForm(Role role, Model model) {
        return "role/create";
    }

    @PostMapping
    public String create(Role role) {
        roleService.create(role);
        return "redirect:/role";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        return "role/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Role role) {
        roleService.update(id, role);
        return "redirect:/role";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/role";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        return "role/detail";
    }

}
