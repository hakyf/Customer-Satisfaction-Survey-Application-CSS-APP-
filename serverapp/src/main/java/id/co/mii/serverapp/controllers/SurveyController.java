package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.dto.request.AnswerQuestionRequest;
import id.co.mii.serverapp.services.SurveyService;
import lombok.AllArgsConstructor;

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

    // menambahkan get maping code
    @GetMapping("/{code}")
    public Survey formByCode(@PathVariable UUID code) {
        return surveyService.formByCode(code);
    }

    @PostMapping("/saveAnswer/{surveyId}")
    public void saveAnswer(@PathVariable Long surveyId, @RequestBody AnswerQuestionRequest answerRequest) {
        Survey survey = surveyService.getById(surveyId);
        surveyService.saveAnswer(survey, answerRequest);
    }

}
