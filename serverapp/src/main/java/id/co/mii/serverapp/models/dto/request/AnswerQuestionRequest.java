package id.co.mii.serverapp.models.dto.request;

import java.util.List;

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
    private List<String> rating;
    private List<Question> question;
    private Survey survey;
    
}
