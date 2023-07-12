package id.co.mii.serverapp.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Answer;
import id.co.mii.serverapp.models.Question;
import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.dto.request.AnswerQuestionRequest;
import id.co.mii.serverapp.repository.AnswerRepository;
import id.co.mii.serverapp.repository.QuestionRepository;
import id.co.mii.serverapp.repository.SurveyRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnswerService {
    private AnswerRepository answerRepository;
    private SurveyRepository surveyRepository;
    private QuestionRepository questionRepository;

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer getById(Long id) {
        return answerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found !"));
    }

    public Answer create(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer update(long id, Answer answer) {
        getById(id);
        answer.setId(id);
        return answerRepository.save(answer);
    }

    public Answer delete(long id) {
        Answer answer = getById(id);
        answerRepository.delete(answer);
        return answer;
    }

    public AnswerQuestionRequest saveAnswer(AnswerQuestionRequest answerQuestionRequest) {
        // Mengambil data dari request
        Long surveyId = answerQuestionRequest.getSurvey().getId();
        Long questionId = answerQuestionRequest.getQuestion().getId();
        String rating = answerQuestionRequest.getRating();

        // Mengambil entitas Survey dan Question berdasarkan ID
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found"));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        // Membuat objek Answer dan mengisi atributnya
        Answer answer = new Answer();
        answer.setRating(rating);
        answer.setSurvey(survey);
        answer.setQuestion(question);
        // Menyimpan entitas Answer ke dalam database
        Answer savedAnswer = answerRepository.save(answer);

        // Membuat DTO dan mengisi dengan data yang disimpan
        AnswerQuestionRequest answerTheQuestion = new AnswerQuestionRequest();
        answerTheQuestion.setId(savedAnswer.getId());
        answerTheQuestion.setRating(savedAnswer.getRating());
        answerTheQuestion.setQuestion(savedAnswer.getQuestion());
        answerTheQuestion.setSurvey(savedAnswer.getSurvey());

        return answerTheQuestion;
    }
}