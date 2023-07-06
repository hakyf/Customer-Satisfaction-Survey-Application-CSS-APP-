package id.co.mii.serverapp.services;

import java.io.File;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.thymeleaf.spring5.SpringTemplateEngine;

import id.co.mii.serverapp.models.Survey;
import id.co.mii.serverapp.models.dto.request.EmailRequest;

import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
    private Survey survey;
    private JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    public EmailRequest sendSimpleMessage(EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());
        mailSender.send(message);
        return emailRequest;
    }


    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText());

            FileSystemResource file = new FileSystemResource(
                new File(emailRequest.getAttach())
            );

            helper.addAttachment(file.getFilename(),file);
            mailSender.send(message);

            System.out.println();
            System.out.println("Email success to send..");
            System.out.println();

        } catch(Exception e) {
            throw new IllegalStateException("email failed to send...");
        }
        return emailRequest;
    }

    public EmailRequest sendMessageWithTemplate(EmailRequest emailRequest){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            Context context = new Context();
            context.setVariable("name", emailRequest.getSurvey().getName());
            context.setVariable("code", emailRequest.getSurvey().getCode());
            context.setVariable("name", emailRequest.getName());
            context.setVariable("code", emailRequest.getCode());
            context.setVariable("text", emailRequest.getText());
            String htmlContent = templateEngine.process("EmailService", context);

            helper.setText(htmlContent, true);

            if (emailRequest.getAttach() != null) {
                FileSystemResource file = new FileSystemResource(emailRequest.getAttach());
                helper.addAttachment(file.getFilename(), file);
            }
            
            mailSender.send(message);

            System.out.println();
            System.out.println("Email success to send...");
            System.out.println();

        } catch (Exception e) {
            throw new IllegalStateException("Email failed to send!!!");
        }

        return emailRequest;
    }   
    }
