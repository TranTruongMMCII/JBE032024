package com.r2s.java_backend_03.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.java_backend_03.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByName(String name);
}
