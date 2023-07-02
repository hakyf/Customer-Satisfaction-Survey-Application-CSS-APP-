package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Section;
import id.co.mii.clientapp.service.SectionService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/section")
@AllArgsConstructor
public class SectionController {

    private SectionService sectionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("section", sectionService.getAll());
        model.addAttribute("isActive", "section");
        return "section/index";
    }

    @GetMapping("/create")
    public String createForm(Section section, Model model) {
        return "section/create";
    }

    @PostMapping
    public String create(Section section) {
        sectionService.create(section);
        return "redirect:/section";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionService.getById(id));
        return "section/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Section section) {
        sectionService.update(id, section);
        return "redirect:/section";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        sectionService.delete(id);
        return "redirect:/section";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionService.getById(id));
        return "section/detail";
    }

}
