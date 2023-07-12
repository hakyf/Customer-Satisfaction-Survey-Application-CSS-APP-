package id.co.mii.clientapp.service;

import org.springframework.stereotype.Service;

import id.co.mii.clientapp.model.Question;
import id.co.mii.clientapp.model.Survey;

@Service
public class AnswerQuestionRequest {
    private Long id;
    private String[] answerRating;
    private Question question;
    private Survey survey;

    // Buatlah konstruktor, getter, dan setter sesuai kebutuhan
    // ...

    public AnswerQuestionRequest() {
        // Konstruktor kosong
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getAnswerRating() {
        return answerRating;
    }

    public void setAnswerRating(String[] answerRating) {
        this.answerRating = answerRating;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
