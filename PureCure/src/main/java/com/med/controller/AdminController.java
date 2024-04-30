package com.med.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.med.exception.AdminAlreadyExistsException;
import com.med.model.Admin;
import com.med.model.Customer;
import com.med.model.SignupDto;
import com.med.repository.AdminRepository;
import com.med.repository.CustomerRepository;
import com.med.serviceinetrface.AdminService;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
@Autowired
private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/addAdmins")
	public ResponseEntity<Admin> addNewAdmin(@RequestBody Admin admin) throws AdminAlreadyExistsException{
		admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
	 	Admin ad = adminService.addNewAdmin(admin);
	 	
	 	return new ResponseEntity<Admin>(ad, HttpStatus.CREATED);
	}
	

	@PatchMapping("/updateAdmins")
	public ResponseEntity<Admin> updateAdminDetails(@RequestBody Admin admin){
		Admin admin1 = adminService.updateAdmin(admin);
		return new ResponseEntity<Admin>(admin1, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAdmins/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer adminId){
		Admin newAdmin = adminService.deleteAdminById(adminId);
		return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
	}
	


	@GetMapping("/getAdminsById/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Integer adminId){
		Admin newAdmin = adminService.findAdminById(adminId);
		return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
	}
	
	@GetMapping("/getAllAdmins")
	public ResponseEntity<List<Admin>> getAdminById(){
		 List<Admin> adminList = adminService.findAllAdmin();
		return new ResponseEntity<>(adminList, HttpStatus.OK);
	}
	 @GetMapping("/logini")
		public ResponseEntity<SignupDto> logInUserHandler(Authentication auth){
			 Admin opt= adminRepository.findByAdminEmail(auth.getName());
			 Optional<Customer> customer=customerRepository.findBycustomerEmail(auth.getName());
			 if(opt==null && customer.get()==null) throw new RuntimeException("No user found") ;
			 SignupDto sd=new SignupDto();
			 if(opt!=null) {
				 sd.setCustomerfirstName(opt.getAdminName());
				 sd.setId(opt.getAdminId());
				 return new ResponseEntity<>(sd, HttpStatus.ACCEPTED);
			 }else {
				 sd.setCustomerfirstName(customer.get().getCustomerfirstName());
				 sd.setId(customer.get().getCustomerId());
				 return new ResponseEntity<>(sd, HttpStatus.ACCEPTED);
			 }
			 
//			 Admin user = opt;
//			 System.out.println(user);
//			 return new ResponseEntity<>(user.getAdminName()+" Logged In Successfully", HttpStatus.ACCEPTED);
		}       
	
	
}
