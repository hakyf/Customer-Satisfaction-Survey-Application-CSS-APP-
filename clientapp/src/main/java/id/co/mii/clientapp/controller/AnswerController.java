package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Answer;
import id.co.mii.clientapp.service.AnswerService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {

    private AnswerService answerService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("answer", answerService.getAll());
        model.addAttribute("isActive", "answer");
        return "answer/index";
    }

    @GetMapping("/create")
    public String createForm(Answer answer, Model model) {
        return "answer/create";
    }

    @PostMapping
    public String create(Answer answer) {
        answerService.create(answer);
        return "redirect:/answer";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("answer", answerService.getById(id));
        return "answer/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Answer answer) {
        answerService.update(id, answer);
        return "redirect:/answer";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        answerService.delete(id);
        return "redirect:/answer";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("answer", answerService.getById(id));
        return "answer/detail";
    }

}
