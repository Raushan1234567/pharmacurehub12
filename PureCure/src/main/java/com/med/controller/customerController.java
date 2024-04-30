package com.med.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.Admin;
import com.med.model.Customer;
import com.med.repository.CustomerRepository;
import com.med.serviceimplementation.CustomerImplementation;
import com.med.serviceinetrface.CustomerInterface;


import lombok.experimental.PackagePrivate;


@CrossOrigin(origins = "*")
@RestController
public class customerController {

	@Autowired
	private CustomerInterface customerInterface;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerImplementation customerImplementation;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/AddCustomers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
		return new ResponseEntity<Customer>(customerInterface.createCustomer(customer),HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/customers/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerInterface.updateCustomer(customerId, customer.getCustomerfirstName(),customer.getCustomerlastName(),customer.getCustomerAddress(),customer.getCustomerPassword()),HttpStatus.CREATED);

		
	}
	
	@GetMapping("/findById/{customerId}")
	public ResponseEntity<Customer> FindById(@PathVariable Integer customerId){
		return new ResponseEntity<Customer>(customerImplementation.FindById(customerId),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findbyemail/{customerEmail}")
	public ResponseEntity<Customer> findbyemail(@PathVariable String customerEmail){
		return new ResponseEntity<Customer>(customerInterface.findByEmail(customerEmail),HttpStatus.OK);
		
	}
	
	@GetMapping("/findAllCustomers")
	public ResponseEntity<List<Customer>> findAllCustomers(){
		return new ResponseEntity<>(customerInterface.findAllCustomer(),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable Integer customerId){
		return new ResponseEntity<>(customerInterface.deleteCustomerById(customerId) ,HttpStatus.OK);
		
	}
	
	@PatchMapping("/upadateCustomers/{customerId}")
	public ResponseEntity<Customer> updateCustomerPassword(@PathVariable Integer customerId, @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerInterface.updateCustomerPassword(customerId,customer.getCustomerPassword()),HttpStatus.CREATED);

		
	}
	
     
	
	
	 
	
}
