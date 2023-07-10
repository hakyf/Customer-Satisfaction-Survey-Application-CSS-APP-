package id.co.mii.serverapp.services;

import java.time.format.DateTimeFormatter;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import id.co.mii.serverapp.models.dto.request.EmailRequest;

import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    // public EmailRequest sendSimpleMessage(EmailRequest emailRequest){
    // SimpleMailMessage message = new SimpleMailMessage();

    // message.setTo(emailRequest.getTo());
    // message.setSubject(emailRequest.getSubject());
    // message.setText(emailRequest.getText());
    // mailSender.send(message);
    // return emailRequest;
    // }

    // public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
    // try{
    // MimeMessage message = mailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(message, true);

    // helper.setTo(emailRequest.getTo());
    // helper.setSubject(emailRequest.getSubject());
    // helper.setText(emailRequest.getText());

    // mailSender.send(message);

    // System.out.println();
    // System.out.println("Email success to send..");
    // System.out.println();

    // } catch(Exception e) {
    // throw new IllegalStateException("email failed to send...");
    // }
    // return emailRequest;
    // }

    public EmailRequest sendSurvey(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            Context context = new Context();
            context.setVariable("name", emailRequest.getName());
            context.setVariable("code", emailRequest.getCode());
            context.setVariable("expired", emailRequest.getExpired());
            context.setVariable("employee", emailRequest.getEmployee().getName());
            context.setVariable("EmailTemplate", "EmailTemplate");

            String htmlContent = templateEngine.process("EmailTemplate", context);

            helper.setText(htmlContent, true);
            message.getAllHeaders();
            mailSender.send(message);

            System.out.println();
            System.out.println("Email success to send...");
            System.out.println();

        } catch (Exception e) {
            throw new IllegalStateException("Email failed to send!!!");
        }

        return emailRequest;
    }

    // private UUID generateRandomCode(UUID code) {
    // // Generate a random code using UUID
    // code = UUID.randomUUID();

    // return code;
    // }
}
