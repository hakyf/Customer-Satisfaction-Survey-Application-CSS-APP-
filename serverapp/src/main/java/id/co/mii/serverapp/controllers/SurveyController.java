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
import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.services.SurveyService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/survey")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class SurveyController {
private SurveyService surveyService;
//  @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Survey> getAll(){
        return surveyService.getAll();
    }
// @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Survey getById(@PathVariable Long id){
        return surveyService.getById(id);
    }
// @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Survey create(@RequestBody Survey survey){
        return surveyService.create(survey);
    }
    
//   @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Survey update(@PathVariable Integer id,@RequestBody Survey survey){
        return surveyService.update(id, survey);
    }
//  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Survey delete(@PathVariable Integer id){
        return surveyService.delete(id);
    }
    
    
}
