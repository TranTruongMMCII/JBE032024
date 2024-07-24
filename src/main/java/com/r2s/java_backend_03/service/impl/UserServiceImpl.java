package com.r2s.java_backend_03.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.r2s.java_backend_03.constants.ERole;
import com.r2s.java_backend_03.dto.request.UserRequestDTO;
import com.r2s.java_backend_03.exception.UserNotFoundException;
import com.r2s.java_backend_03.mapper.JBE03Mapper;
import com.r2s.java_backend_03.model.Identification;
import com.r2s.java_backend_03.model.Role;
import com.r2s.java_backend_03.model.User;
import com.r2s.java_backend_03.repository.RoleRepository;
import com.r2s.java_backend_03.repository.UserRepository;
import com.r2s.java_backend_03.service.UserService;
import com.r2s.java_backend_03.validator.UserValidator;

import lombok.RequiredArgsConstructor;

@Service(value = "UserServiceA")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired // field injection
	private UserRepository userRepository;
	
	@Autowired
	private JBE03Mapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

//	private final UserRepository userRepository;

	@Override
	public List<User> getUsers(Pageable pageable) {
		Page<User> users = this.userRepository.findAll(pageable);
		return users.stream().toList();
	}

	@Override
	public User getById(int id) {
		return this.userRepository.findById(id).get();
	}

	@Override
	public User save(UserRequestDTO userRequestDTO) {
		User user = this.mapper.toModel(userRequestDTO);
//		Identification identification = Identification.builder()
//				.expiredDate(userRequestDTO.getExpiredDate())
//				.build();
//		user.setIdentification(identification);
		UserValidator.validateSavedUser(user);
		
		// get role
		Role role = this.roleRepository.findByName(ERole.USER.name()).get();
		
		user.getRoles().add(role);
		user.setPassword(this.passwordEncoder.encode(userRequestDTO.getPassword()));

		return this.userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		this.userRepository.delete(user);
	}

	@Override
	public List<User> getUsers(Boolean isDeleted) {
		if (Objects.isNull(isDeleted)) {
			return this.userRepository.findAll();
		}

		return this.userRepository.findByDeleted(isDeleted);
	}

	@Override
	public List<User> getUsers(Boolean isDeleted, String name) {
		if (Objects.isNull(isDeleted) && StringUtils.isEmpty(name)) {
			return this.userRepository.findAll();
		}

		if (Objects.isNull(isDeleted)) {
			return this.userRepository.findByNameContains(name);
		}

		if (StringUtils.isEmpty(name)) {
			return this.userRepository.findByDeleted(isDeleted);
		}

		return this.userRepository.findByDeletedAndNameContains(isDeleted, name);
	}

	@Override
	public User update(UserRequestDTO userRequestDTO) {
		User user = this.mapper.toModel(userRequestDTO);
		UserValidator.validateUpdatedUser(user);

		// tim user can cap nhat
		Optional<User> optionalFoundUser = this.userRepository.findById(user.getId());
		if (optionalFoundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		// cap nhat thong tin
		User foundUser = optionalFoundUser.get();
		foundUser.setName(user.getName());
		foundUser.setEmail(user.getEmail());
		foundUser.setDeleted(user.getDeleted());
		Identification identification = foundUser.getIdentification();
		identification.setExpiredDate(userRequestDTO.getExpiredDate());
		foundUser.setIdentification(identification);

		return this.userRepository.save(foundUser);
	}

}
