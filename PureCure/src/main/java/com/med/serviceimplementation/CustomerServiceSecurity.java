package com.med.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.med.model.Customer;
import com.med.repository.CustomerRepository;


public class CustomerServiceSecurity implements UserDetailsService{
	
	@Autowired
	private CustomerRepository uRepo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Customer> user = uRepo.findBycustomerEmail(username) ;
		if(user.isEmpty()) throw new UsernameNotFoundException("user not found") ;
		Customer us = user.get();

		List<GrantedAuthority> authorities = new ArrayList<>() ;
		SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole().toUpperCase()) ;
		authorities.add(autho) ;
	    User secUser = new User(us.getCustomerEmail(), us.getCustomerPassword(),  authorities) ;
		return secUser ;

	}

}
