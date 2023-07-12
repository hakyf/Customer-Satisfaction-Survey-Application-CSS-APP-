package id.co.mii.serverapp.models.dto.request;

import id.co.mii.serverapp.models.Question;
import id.co.mii.serverapp.models.Survey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerQuestionRequest {

    private Long id;
    private String[] answerRating;
    private Question question;
    private Survey survey;

}
