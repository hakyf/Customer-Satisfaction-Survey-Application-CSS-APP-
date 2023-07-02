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

import id.co.mii.clientapp.model.Question;
import id.co.mii.clientapp.service.QuestionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
public class QuestionRestController {

    private QuestionService questionService;

    @GetMapping
    public List<Question> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id) {
        return questionService.getById(id);
    }

    @PostMapping
    public Question create(@RequestBody Question question) {
        return questionService.create(question);
    }

    @PutMapping("/{id}")
    public Question update(@PathVariable Long id, @RequestBody Question question) {
        return questionService.update(id, question);
    }

    @DeleteMapping("/{id}")
    public Question delete(@PathVariable Long id) {
        return questionService.delete(id);
    }

}
