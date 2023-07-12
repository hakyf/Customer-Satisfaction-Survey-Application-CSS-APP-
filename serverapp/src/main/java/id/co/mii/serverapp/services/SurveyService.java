package id.co.mii.serverapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Answer;
import id.co.mii.serverapp.models.Client;
import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Question;
import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.Status;
import id.co.mii.serverapp.models.dto.request.AnswerQuestionRequest;
import id.co.mii.serverapp.models.dto.request.EmailRequest;
import id.co.mii.serverapp.repository.AnswerRepository;
import id.co.mii.serverapp.repository.SurveyRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService {
    private SurveyRepository surveyRepository;
    private EmployeeService employeeService;
    private EmailService emailService;
    private ClientService clientService;
    private StatusService statusService;
    private AnswerService answerService;
    private AnswerRepository answerRepository;

    public List<Survey> getAll() {
        return surveyRepository.findAll();
    }

    public Survey getById(Long id) {
        return surveyRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));
    }

    public Survey create(Survey survey) {
        survey.setName("Customer Satisfaction Survey - Metrodata Electronics");
        UUID code = UUID.randomUUID();
        survey.setCode(code);
        LocalDate expired = LocalDate.now().plusDays(7);
        survey.setExpired(expired);

        // Set ID status menjadi 1
        Status status = statusService.getById(1L);
        survey.setStatus(status);

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
        Survey survey = surveyRepository
                .findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));

        // Lakukan logika untuk mengirimkan email dan menyimpan jawaban

        return survey;
    }

    public void saveAnswer(Survey survey, AnswerQuestionRequest answerRequest) {
        // Ambil nilai-nilai dari AnswerQuestionRequest
        Long surveyId = answerRequest.getId();
        String[] answerRatings = answerRequest.getAnswerRating();
        Question question = answerRequest.getQuestion();

        // Konversi array menjadi list
        List<String> ratings = Arrays.asList(answerRatings);

        // Buat objek Answer
        Answer answer = new Answer();
        answer.setId(surveyId);
        for (String rating : ratings) {
            answer.setRating(rating);
        }
        answer.setQuestion(question);

        // Simpan jawaban ke dalam database
        answerRepository.save(answer);
    }

}