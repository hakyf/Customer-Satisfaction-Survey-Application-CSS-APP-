package id.co.mii.clientapp.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.clientapp.model.Result;
import id.co.mii.clientapp.model.Survey;
import id.co.mii.clientapp.service.ResultService;
import id.co.mii.clientapp.service.SurveyService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/result")
@AllArgsConstructor
public class ResultRestController {

    private ResultService resultService;
    private SurveyService surveyService;

    @GetMapping
    public List<Survey> getAll() {
        List<Survey> surveys = surveyService.getAll().stream().filter(S -> S.getResult() != null)
                .collect(Collectors.toList());
        return surveys;
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return resultService.getById(id);
    }

    @PostMapping
    public Result create(@RequestBody Result result) {
        return resultService.create(result);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Result result) {
        return resultService.update(id, result);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return resultService.delete(id);
    }

}
