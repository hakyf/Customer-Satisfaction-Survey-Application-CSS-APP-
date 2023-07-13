package id.co.mii.clientapp.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Survey;
import id.co.mii.clientapp.service.AnswerQuestionRequest;
import id.co.mii.clientapp.service.ClientService;
import id.co.mii.clientapp.service.EmployeeService;
import id.co.mii.clientapp.service.ParameterService;
import id.co.mii.clientapp.service.QuestionService;
import id.co.mii.clientapp.service.SectionService;
import id.co.mii.clientapp.service.SurveyService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/survey")
@AllArgsConstructor
public class SurveyController {

    private SurveyService surveyService;
    private SectionService sectionService;
    private QuestionService questionService;
    private ParameterService parameterService;
    private EmployeeService employeeService;
    private ClientService clientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("survey", surveyService.getAll());
        model.addAttribute("isActive", "survey");
        return "survey/index";
    }

    @GetMapping("/create")
    public String createForm(Survey survey, Model model) {
        return "survey/create";
    }

    @PostMapping
    public String create(Survey survey) {
        surveyService.create(survey);
        return "redirect:/survey";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("survey", surveyService.getById(id));
        return "survey/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Survey survey) {
        surveyService.update(id, survey);
        return "redirect:/survey";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        surveyService.delete(id);
        return "redirect:/survey";
    }

    @GetMapping("/c/{code}")
    public String formByCode(@PathVariable String code, Model model) {
        Survey survey = surveyService.formByCode(code);
        model.addAttribute("survey", survey);
        model.addAttribute("sections", sectionService.getAll());
        model.addAttribute("questions", questionService.getAll());
        model.addAttribute("parameters", parameterService.getAll());
        model.addAttribute("client", survey.getClient().getName());
        model.addAttribute("employee", survey.getEmployee().getName());
        return "survey/formByCode";
    }

    @PostMapping("/saveAnswer/{surveyId}")
    public String saveAnswer(@PathVariable Long surveyId, AnswerQuestionRequest answerRequest) {
        surveyService.saveAnswer(surveyId, answerRequest);
        return "redirect:/survey";
    }

}