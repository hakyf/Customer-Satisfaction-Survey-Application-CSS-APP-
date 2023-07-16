package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.dto.request.QuestionAnswerRequest;
import id.co.mii.serverapp.services.SurveyService;
import lombok.AllArgsConstructor;

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

    @GetMapping("/c/{code}")
    public Survey formByCode(@PathVariable String code) {
        return surveyService.formByCode(code);
    }

    @PostMapping("/answer/{code}")
    public ResponseEntity<Survey> sendSurveyAnswer(@PathVariable String code,
            @RequestBody List<QuestionAnswerRequest> qar) {
        Survey survey = surveyService.sendSurveyAnswer(code, qar);
        return ResponseEntity.ok(survey);
    }

}
