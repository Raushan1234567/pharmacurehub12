package com.med.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.PureCureApplication;
import com.med.model.Response;
import com.med.serviceimplementation.emailSenderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Mail")
public class MailController {

	@Autowired
	private emailSenderService emailSenderService;
	
	@Autowired
	private PureCureApplication mailSystemApplication;

	@PostMapping("/sendMail/{email}")
	public ResponseEntity<Response> sendEmail(@PathVariable String email){
		  
		        Random random = new Random();
		        int randomNumber = 10000 + random.nextInt(90000);
		     
		 String body="Thank you for taking the time to verify your email with us. To complete the verification process, please use the following five-digit verification CODE : "+randomNumber+". \n \n";
	     String body2="Please enter this code in the designated field on the verification page to confirm and activate your email account. If you did not initiate this verification process, please disregard this email.";
	   	   
		emailSenderService.sendEmail(email, "Email Verification Code", body+" "+body2+"\n \n Thank you for choosing our service.");
		return new ResponseEntity<Response>(new Response(0,"Email sent successfully",randomNumber),HttpStatus.OK);
		
	}

}
