package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Answer;

import id.co.mii.serverapp.repository.AnswerRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AnswerService {
    private AnswerRepository answerRepository;
    public List<Answer> getAll(){
        return answerRepository.findAll();
    }
    public Answer getById(Long id){
        return answerRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found !")
        );
    }
    
    public Answer create(Answer answer){
        return answerRepository.save(answer);
    }

    public Answer update(long id, Answer answer){
        getById(id);
        answer.setId(id);
        return answerRepository.save(answer); 
    }

    public Answer delete(long id){
        Answer answer= getById(id);
        answerRepository.delete(answer);
        return answer;
    }
    
}
