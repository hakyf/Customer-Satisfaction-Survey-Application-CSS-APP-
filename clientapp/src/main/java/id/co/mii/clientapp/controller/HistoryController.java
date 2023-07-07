package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.History;
import id.co.mii.clientapp.service.HistoryService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/history")
@AllArgsConstructor
public class HistoryController {

    private HistoryService historyService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("history", historyService.getAll());
        model.addAttribute("isActive", "history");
        return "history/index";
    }

    @GetMapping("/create")
    public String createForm(History history, Model model) {
        return "history/create";
    }

    @PostMapping
    public String create(History history) {
        historyService.create(history);
        return "redirect:/history";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("history", historyService.getById(id));
        return "history/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, History history) {
        historyService.update(id, history);
        return "redirect:/history";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        historyService.delete(id);
        return "redirect:/history";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("history", historyService.getById(id));
        return "history/detail";
    }
}
