package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Question;
import id.co.mii.clientapp.service.QuestionService;
import id.co.mii.clientapp.service.SectionService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;
    private SectionService sectionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("question", questionService.getAll());
        model.addAttribute("isActive", "question");
        return "question/index";
    }

    @GetMapping("/create")
    public String createForm(Question question, Model model) {
        model.addAttribute("section", sectionService.getAll());
        return "question/create";
    }

    @PostMapping
    public String create(Question question) {
        questionService.create(question);
        return "redirect:/question";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionService.getAll());
        model.addAttribute("question", questionService.getById(id));
        return "question/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Question question) {
        questionService.update(id, question);
        return "redirect:/question";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        questionService.delete(id);
        return "redirect:/question";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.getById(id));
        return "question/detail";
    }

}
