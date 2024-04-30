package com.med.serviceimplementation;



import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String toEmail, String subject, String string2) {
       

        SimpleMailMessage message = new SimpleMailMessage();
       
   		message.setFrom("rk70705082@gmail.com");
        message.setTo(toEmail);
        message.setText(string2);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Email sent successfully");

        return null; 
    }


  
}

