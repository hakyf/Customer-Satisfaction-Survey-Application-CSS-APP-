package id.co.mii.serverapp.controllers;

import java.util.List;

// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.mii.serverapp.models.Question;
import id.co.mii.serverapp.models.Section;
import id.co.mii.serverapp.services.QuestionService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class QuestionController {
    private QuestionService questionService;

    // @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Question> getAll() {
        return questionService.getAll();
    }

    // @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id) {
        return questionService.getById(id);
    }

    // @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Question create(@RequestBody Question question) {
        return questionService.create(question);
    }

    // @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Question update(@PathVariable Long id, @RequestBody Question question) {
        return questionService.update(id, question);
    }

    // @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Question delete(@PathVariable Long id) {
        return questionService.delete(id);
    }

    @GetMapping("/section/{sectionId}")
    public List<Question> getQuestionsBySection(@PathVariable Long sectionId) {
        Section section = new Section();
        section.setId(sectionId);
        return questionService.getQuestionsBySection(section);
    }
}
