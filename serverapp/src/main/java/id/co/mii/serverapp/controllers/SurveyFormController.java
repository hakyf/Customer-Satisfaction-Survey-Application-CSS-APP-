// package id.co.mii.serverapp.controllers;

// import java.time.LocalDateTime;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import id.co.mii.serverapp.models.dto.request.EmailRequest;
// import id.co.mii.serverapp.services.EmailService;
// import id.co.mii.serverapp.services.SurveyService;
// import id.co.mii.serverapp.models.Survey;
// import lombok.AllArgsConstructor;

// @RestController
// @AllArgsConstructor
// @RequestMapping("/email")
// public class SurveyFormController {
//     @Autowired
//     private SurveyService surveyService;

//     private EmailService emailService;

//     @PostMapping("/sendunique")
//     public ResponseEntity<String> sendEmail(@RequestBody Long id, @RequestBody EmailRequest emailRequest) {
//         Survey survey = surveyService.getById(id);
        
//         LocalDateTime expirationTime = LocalDateTime.now().plusSeconds(5);
//         emailRequest.setExpirationTime(expirationTime);
//         emailRequest.setCode(survey.getCode());
//         emailRequest.setName(survey.getName());    
//         emailService.sendMessageWithTemplate(emailRequest, survey);

//         return ResponseEntity.ok("Email sent successfully!");
//     }

//     @RestController
// @RequestMapping("/survey")
// @AllArgsConstructor
// public class SurveyController {

//     private final SurveyService surveyService;

// //   @PostMapping("/send")
// //     public ResponseEntity<String> sendSurvey(@RequestBody EmailRequest emailRequest) {
// //         Survey survey = surveyService.getByCode(emailRequest.getCode());
// //         if (survey == null) {
// //             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Survey not found");
// //         }
// //         surveyService.sendSurvey(survey, emailRequest);
// //         return ResponseEntity.ok("Survey sent successfully");
// //     }


//     // ...
// }


// }
