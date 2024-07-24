package com.r2s.java_backend_03.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.r2s.java_backend_03.exception.UserNotFoundException;
import com.r2s.java_backend_03.model.Address;
import com.r2s.java_backend_03.model.User;
import com.r2s.java_backend_03.repository.AddressRepository;
import com.r2s.java_backend_03.repository.UserRepository;
import com.r2s.java_backend_03.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final UserRepository userRepository;
	private final AddressRepository addressRepository;

	@Override
	public Address save(Address address) {
		// validation....

		// check user exists
		User addressUser = address.getUser();
		Optional<User> optionalUser = this.userRepository.findById(addressUser.getId());
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException();
		}

//		// insert by user
//		User foundUser = optionalUser.get();
//		foundUser.getAddresses().add(address);
//		this.userRepository.save(foundUser);
		
		// insert by address
		User foundUser = optionalUser.get();
		address.setUser(foundUser);

		return this.addressRepository.save(address);
	}

}
