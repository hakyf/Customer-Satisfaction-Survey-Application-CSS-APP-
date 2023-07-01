package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Question;

import id.co.mii.serverapp.repository.QuestionRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class QuestionService {
    private QuestionRepository questionRepository;
    public List<Question> getAll(){
        return questionRepository.findAll();
    }
    public Question getById(Long id){
        return questionRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found !")
        );
    }
    
    public Question create(Question question){
        return questionRepository.save(question);
    }

    public Question update(long id, Question question){
        getById(id);
        question.setId(id);
        return questionRepository.save(question); 
    }

    public Question delete(long id){
        Question question= getById(id);
        questionRepository.delete(question);
        return question;
    }
    
}
