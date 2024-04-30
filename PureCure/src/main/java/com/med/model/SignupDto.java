package com.med.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;


@CrossOrigin(origins = "*")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
	private int id;
    private String customerfirstName;
    private String customerEmail;
    private String customerPassword;
    private String role;
    
    
}
