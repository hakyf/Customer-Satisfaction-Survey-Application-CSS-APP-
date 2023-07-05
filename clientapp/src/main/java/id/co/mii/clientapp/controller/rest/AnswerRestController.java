package id.co.mii.clientapp.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.clientapp.model.Answer;
import id.co.mii.clientapp.service.AnswerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/answer")
@AllArgsConstructor
public class AnswerRestController {

    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAll() {
        return answerService.getAll();
    }

    @GetMapping("/{id}")
    public Answer getById(@PathVariable Long id) {
        return answerService.getById(id);
    }

    @PostMapping
    public Answer create(@RequestBody Answer answer) {
        return answerService.create(answer);
    }

    @PutMapping("/{id}")
    public Answer update(@PathVariable Long id, @RequestBody Answer answer) {
        return answerService.update(id, answer);
    }

    @DeleteMapping("/{id}")
    public Answer delete(@PathVariable Long id) {
        return answerService.delete(id);
    }

}
