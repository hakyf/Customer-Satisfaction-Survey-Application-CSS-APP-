package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Parameter;
import id.co.mii.clientapp.service.ParameterService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/parameter")
@AllArgsConstructor
public class ParameterController {

    private ParameterService parameterService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("parameter", parameterService.getAll());
        model.addAttribute("isActive", "parameter");
        return "parameter/index";
    }

    @GetMapping("/create")
    public String createForm(Parameter parameter, Model model) {
        return "parameter/create";
    }

    @PostMapping
    public String create(Parameter parameter) {
        parameterService.create(parameter);
        return "redirect:/parameter";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("parameter", parameterService.getById(id));
        return "parameter/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Parameter parameter) {
        parameterService.update(id, parameter);
        return "redirect:/parameter";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        parameterService.delete(id);
        return "redirect:/parameter";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("parameter", parameterService.getById(id));
        return "parameter/detail";
    }

}
