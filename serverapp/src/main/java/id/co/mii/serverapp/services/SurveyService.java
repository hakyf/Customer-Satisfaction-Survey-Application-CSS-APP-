package id.co.mii.serverapp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.aspectj.apache.bcel.generic.InstructionConstants.Clinit;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Client;
import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.dto.request.EmailRequest;
import id.co.mii.serverapp.repository.SurveyRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService {
    private SurveyRepository surveyRepository;
    private EmployeeService employeeService;
    private EmailService emailService;
    private ClientService clientService;

    public List<Survey> getAll() {
        return surveyRepository.findAll();
    }

    public Survey getById(Long id) {
        return surveyRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));
    }

    public Survey create(Survey survey) {
        survey.setName("Employee Performance Survey - Metrodata Electronics");
        UUID code = UUID.randomUUID();
        survey.setCode(code);
        LocalDate expired = LocalDate.now().plusDays(7);
        survey.setExpired(expired);

        // Simpan survey ke database
        final Survey createdSurvey = surveyRepository.save(survey);
        Client client = clientService.getById(survey.getClient().getId());
        Employee employee = employeeService.getById(survey.getEmployee().getId());
        String to = client.getEmail();
        String name = client.getName();

        EmailRequest emailRequest = new EmailRequest(to, name, "Invitation to Provide Feedback on Employee Performance",
                createdSurvey.getCode(),
                createdSurvey.getExpired(),
                employee);

        emailService.sendSurvey(emailRequest);

        return createdSurvey;
    }

    public Survey update(long id, Survey survey) {
        getById(id);
        survey.setId(id);
        return surveyRepository.save(survey);
    }

    public Survey delete(long id) {
        Survey survey = getById(id);
        surveyRepository.delete(survey);
        return survey;
    }

    public Survey formByCode(UUID code) {
        return surveyRepository
                .findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));
    }
}
