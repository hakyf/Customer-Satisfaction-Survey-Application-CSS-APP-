package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.services.SurveyService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping
    public List<Survey> getAll() {
        return surveyService.getAll();
    }

    @GetMapping("/{id}")
    public Survey getById(@PathVariable Long id) {
        return surveyService.getById(id);
    }

    @PostMapping
    public Survey create(@RequestBody Survey survey) {
        // Generate UUID for the survey's code property
        // UUID code = UUID.randomUUID();
        // survey.setCode(code);
        return surveyService.create(survey);
    }

    @PutMapping("/{id}")
    public Survey update(@PathVariable Long id, @RequestBody Survey survey) {
        return surveyService.update(id, survey);
    }

    @DeleteMapping("/{id}")
    public Survey delete(@PathVariable Long id) {
        return surveyService.delete(id);
    }

    // menambahkan get maping code
    @GetMapping("/{code}")
    public Survey formByCode(@PathVariable UUID code) {
        return surveyService.formByCode(code);
    }

}
