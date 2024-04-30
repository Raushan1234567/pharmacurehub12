package com.med.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.med.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByAdminEmail(String adminEmail);
	
	

}
