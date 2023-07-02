package id.co.mii.serverapp.controllers;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.mii.serverapp.models.Answer;
import id.co.mii.serverapp.services.AnswerService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/answer")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class AnswerController {
private AnswerService answerService;
//  @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Answer> getAll(){
        return answerService.getAll();
    }
// @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Answer getById(@PathVariable Long id){
        return answerService.getById(id);
    }
// @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Answer create(@RequestBody Answer answer){
        return answerService.create(answer);
    }
    
//   @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Answer update(@PathVariable Integer id,@RequestBody Answer answer){
        return answerService.update(id, answer);
    }
//  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Answer delete(@PathVariable Integer id){
        return answerService.delete(id);
    }
    
    
}
