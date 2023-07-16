package id.co.mii.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.co.mii.clientapp.model.Result;
import id.co.mii.clientapp.model.Survey;
import id.co.mii.clientapp.service.ParameterService;
import id.co.mii.clientapp.service.ResultService;
import id.co.mii.clientapp.service.SurveyService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/result")
@AllArgsConstructor
public class ResultController {

    private ResultService resultService;
    private SurveyService surveyService;
    private ParameterService parameterService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("result", resultService.getAll());
        model.addAttribute("isActive", "result");
        return "result/index";
    }

    @GetMapping("/create")
    public String createForm(Result result, Model model) {
        model.addAttribute("section", resultService.getAll());
        return "result/create";
    }

    @PostMapping
    public String create(Result result) {
        resultService.create(result);
        return "redirect:/result";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("result", resultService.getById(id));
        return "result/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Result result) {
        resultService.update(id, result);
        return "redirect:/result";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        resultService.delete(id);
        return "redirect:/result";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Survey survey = surveyService.getById(id);
        model.addAttribute("survey", survey);
        model.addAttribute("isActive", "result");
        model.addAttribute("parameterSize", parameterService.getAll().size());
        return "result/detail";
    }
}
