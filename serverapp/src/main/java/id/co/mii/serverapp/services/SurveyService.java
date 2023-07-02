package id.co.mii.serverapp.services;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Survey;

import id.co.mii.serverapp.repository.SurveyRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SurveyService {
    private SurveyRepository surveyRepository;
    public List<Survey> getAll(){
        return surveyRepository.findAll();
    }
    public Survey getById(Long id){
        return surveyRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found !")
        );
    }
    
    public Survey create(Survey survey){
        return surveyRepository.save(survey);
    }

    public Survey update(long id, Survey survey){
        getById(id);
        survey.setId(id);
        return surveyRepository.save(survey); 
    }

    public Survey delete(long id){
        Survey survey= getById(id);
        surveyRepository.delete(survey);
        return survey;
    }
    
}
