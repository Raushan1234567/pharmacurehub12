package com.med.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	private String adminName;
	
	private String adminEmail;
	
	private String adminPassword;
	
	private String adminMobileNumber;
	
	private String adminAddress;
	
	private String role;
	

	@JsonIgnore
	@OneToMany
	private List<Customer> customerList=new ArrayList<>();
	

	@JsonIgnore
	@OneToMany
	private List<Medicine> medicineList=new ArrayList<>();
	
	@JsonIgnore
	@OneToMany
	private List<Orders> orderList=new ArrayList<>();
	
	
	
	
}
