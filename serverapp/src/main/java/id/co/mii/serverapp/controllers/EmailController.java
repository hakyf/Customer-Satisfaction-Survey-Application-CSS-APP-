package id.co.mii.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import id.co.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;
 
    // @PostMapping("/simple")
    // public EmailRequest sendSimpleMessaget(
    //     @RequestBody EmailRequest emailRequest
    // ) {
    //     return emailService.sendSimpleMessage(emailRequest);
    // }

    // @PostMapping("/attach")
    // public EmailRequest sendMessageWitchAttachment(@RequestBody EmailRequest emailRequest){
    //     return emailService.sendMessageWithAttachment(emailRequest);
    // }

    @PostMapping("/send")
    public EmailRequest sendSurvey(@RequestBody EmailRequest emailRequest){    
        return emailService.sendSurvey(emailRequest);
    }
}