package com.med.repository;

import java.awt.print.Pageable;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.med.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	
	Optional<Customer> findBycustomerEmail(String customerEmail);

}