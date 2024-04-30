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

import com.med.model.Admin;
import com.med.model.Customer;
import com.med.repository.AdminRepository;
import com.med.repository.CustomerRepository;





@Service
public class AdminSecurityService implements UserDetailsService {
	
	
	@Autowired
	private AdminRepository uRepo;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin user = uRepo.findByAdminEmail(username) ;
		Optional<Customer> customer=customerRepository.findBycustomerEmail(username);
		if(user==null && customer.get()==null)
			throw new UsernameNotFoundException("user not found") ;
	
		if(user!=null) {
			List<GrantedAuthority> authorities = new ArrayList<>() ;
			SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+user.getRole().toUpperCase()) ;
			authorities.add(autho) ;
			User secUser = new User(user.getAdminEmail(), user.getAdminPassword(),  authorities) ;
			return secUser ;
		}else {
			List<GrantedAuthority> authorities = new ArrayList<>() ;
			SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+customer.get().getRole().toUpperCase()) ;
			authorities.add(autho) ;
			User secUser = new User(customer.get().getCustomerEmail(), customer.get().getCustomerPassword(),  authorities) ;
			return secUser ;

			
		}


	}

}
