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
import id.co.mii.clientapp.service.ClientService;
import id.co.mii.clientapp.service.EmployeeService;
import id.co.mii.clientapp.service.SurveyService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/survey")
@AllArgsConstructor
public class SurveyController {

    private SurveyService surveyService;
    private EmployeeService employeeService;
    private ClientService clientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("survey", surveyService.getAll());
        model.addAttribute("isActive", "survey");
        return "survey/index";
    }

    @GetMapping("/send")
    public String sendForm(Survey survey, Model model) {
        model.addAttribute("surveys", surveyService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("isActive", "send");
        return "survey/send";
    }

    @PostMapping
    public String send(Survey survey) {
        surveyService.send(survey);
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

    @GetMapping("/{code}")
    public String formByCode(@PathVariable UUID code) {
        return "survey/formByCode";
    }

    // @GetMapping("/{id}")
    // public String detail(@PathVariable Long id, Model model) {
    // model.addAttribute("survey", surveyService.getById(id));
    // return "survey/detail";
    // }
    // @GetMapping("/{code}")
    // public String formByCode(Model model, @PathVariable UUID code) {
    // model.addAttribute("code", surveyService.formByCode(code));
    // return "survey/formByCode";
    // }

    // @PostMapping
    // public String formByCode(Survey survey, UUID code) {
    // surveyService.formByCode(code);
    // return "redirect:/survey";
    // }
    // @GetMapping("/{code}")
    // public String formByCode(Model model,@PathVariable("code") Survey survey) {
    // model.addAttribute("code", surveyService.formByCode(survey.getCode()));

    // return "survey/formByCode";
    //

}
