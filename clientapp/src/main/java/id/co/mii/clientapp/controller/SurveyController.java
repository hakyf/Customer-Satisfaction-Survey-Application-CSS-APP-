package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Survey;
import id.co.mii.clientapp.service.ParameterService;
import id.co.mii.clientapp.service.SectionService;
import id.co.mii.clientapp.service.SurveyService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/survey")
@AllArgsConstructor
public class SurveyController {

    private SurveyService surveyService;
    private SectionService sectionService;
    private ParameterService parameterService;

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
        if (survey.getStatus().getId() == 2) {
            return "redirect:/survey/error";
        }
        model.addAttribute("survey", survey);
        model.addAttribute("sections", sectionService.getAll());
        model.addAttribute("parameters", parameterService.getAll());
        model.addAttribute("client", survey.getClient().getName());
        model.addAttribute("employee", survey.getEmployee().getName());
        model.addAttribute("position", survey.getEmployee().getJobPosition());
        return "survey/formByCode";
    }

    @GetMapping("/c/{code}/success")
    public String successForm(@PathVariable String code) {
        return "survey/successForm";
    }

    @GetMapping("/error")
    public String errorForm() {
        return "survey/errorForm";
    }

    @PostMapping("/c/{code}/review")
    public String reviewSurvey(@PathVariable String code) {
        surveyService.reviewSurvey(code);
        return "redirect:/result";
    }
}