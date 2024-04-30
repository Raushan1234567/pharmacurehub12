package com.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.SignupDto;
import com.med.repository.CustomerRepository;
import com.med.serviceinetrface.SignupDtoInteface;

@CrossOrigin(origins = "*")
@RestController
public class SignupDtoController {

	@Autowired
	private SignupDtoInteface signupDtoInteface;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	 @PostMapping("/signup")
	    public ResponseEntity<String> signUpUser(@RequestBody SignupDto signupDto) {
		 signupDto.setCustomerPassword(passwordEncoder.encode(signupDto.getCustomerPassword()));
		 signupDtoInteface.signUpUser(signupDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful!");
	       
	    }
	
}
