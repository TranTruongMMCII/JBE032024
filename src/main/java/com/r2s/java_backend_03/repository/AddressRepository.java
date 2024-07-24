package com.r2s.java_backend_03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.java_backend_03.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
