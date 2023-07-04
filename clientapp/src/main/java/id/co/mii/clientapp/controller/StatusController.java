package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Status;
import id.co.mii.clientapp.service.StatusService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/status")
@AllArgsConstructor
public class StatusController {

    private StatusService statusService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("status", statusService.getAll());
        model.addAttribute("isActive", "status");
        return "status/index";
    }

    @GetMapping("/create")
    public String createForm(Status status, Model model) {
        return "status/create";
    }

    @PostMapping
    public String create(Status status) {
        statusService.create(status);
        return "redirect:/status";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("status", statusService.getById(id));
        return "status/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Status status) {
        statusService.update(id, status);
        return "redirect:/status";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        statusService.delete(id);
        return "redirect:/status";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("status", statusService.getById(id));
        return "status/detail";
    }

}
