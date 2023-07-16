package id.co.mii.serverapp.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Answer;
import id.co.mii.serverapp.models.Client;
import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Parameter;
import id.co.mii.serverapp.models.Question;
import id.co.mii.serverapp.models.Result;
import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.Status;
import id.co.mii.serverapp.models.dto.request.EmailRequest;
import id.co.mii.serverapp.models.dto.request.QuestionAnswerRequest;
import id.co.mii.serverapp.repository.SurveyRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService {

    // @Autowired
    private SurveyRepository surveyRepository;
    private EmployeeService employeeService;
    private EmailService emailService;
    private ClientService clientService;
    private StatusService statusService;
    private AnswerService answerService;
    private QuestionService questionService;
    private ParameterService parameterService;

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
        String code = UUID.randomUUID().toString();
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

    public Survey formByCode(String code) {
        return surveyRepository
                .findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));
    }

    public Survey sendSurveyAnswer(String code, List<QuestionAnswerRequest> qar) {
        Survey survey = surveyRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));
        Status status = statusService.getById(2L);
        survey.setStatus(status);

        List<Answer> answers = new ArrayList<Answer>();
        double totalScore = 0;
        int questionCount = 0;
        for (QuestionAnswerRequest qa : qar) {
            Answer answer = new Answer();
            Question question = questionService.getById(qa.getQuestionId());
            answer.setQuestion(question);
            String ans = qa.getAnswer().toString();
            Parameter param = parameterService.getByNotes(ans);
            if (param != null) {
                double ansVal = Double.valueOf(param.getValue());
                totalScore += ansVal;
                questionCount++;
            }
            answer.setRating(qa.getAnswer());
            answer.setSurvey(survey);

            answer = answerService.create(answer);
            answers.add(answer);
        }
        double meanScore = 0.0;
        if (questionCount > 0) {
            meanScore = totalScore / questionCount;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedMeanScore = df.format(meanScore);

        LocalDate date = LocalDate.now();

        Result result = new Result();
        result.setMean(formattedMeanScore);
        result.setScore(String.valueOf(totalScore));
        result.setSurvey(survey);
        result.setDate(date);
        survey.setResult(result);
        survey.setAnswers(answers);

        return surveyRepository.save(survey);
    }

    public Survey reviewSurvey(String code) {
        Survey survey = surveyRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !"));
        Status status = statusService.getById(3L);
        survey.setStatus(status);

        return surveyRepository.save(survey);
    }

}