package id.co.mii.serverapp.services;
import java.util.List;
import org.springframework.stereotype.Service;
import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.repository.SurveyRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyRequestService {
    private SurveyRepository surveyRepository;
  private SurveyService surveyService;
  private StatusService statusService;
  private UserService userService;
  private EmployeeService employeeService;
  private ClientService clientService;
private EmailService emailService;

public List<Survey> getAll(){
    return surveyRepository.findAll();
}

}
    

