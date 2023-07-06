package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Client;
import id.co.mii.clientapp.service.ClientService;
import id.co.mii.clientapp.service.SectionService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;
    private SectionService sectionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("client", clientService.getAll());
        model.addAttribute("isActive", "client");
        return "client/index";
    }

    @GetMapping("/create")
    public String createForm(Client client, Model model) {
        model.addAttribute("section", sectionService.getAll());
        return "client/create";
    }

    @PostMapping
    public String create(Client client) {
        clientService.create(client);
        return "redirect:/client";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionService.getAll());
        model.addAttribute("client", clientService.getById(id));
        return "client/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Client client) {
        clientService.update(id, client);
        return "redirect:/client";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/client";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getById(id));
        return "client/detail";
    }

}
