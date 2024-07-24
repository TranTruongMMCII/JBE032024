package com.r2s.java_backend_03.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.r2s.java_backend_03.dto.request.UserRequestDTO;
import com.r2s.java_backend_03.model.User;

public interface UserService {
	
	List<User> getUsers(Pageable pageable);

	List<User> getUsers(Boolean isDeleted);

	List<User> getUsers(Boolean isDeleted, String name);

	User getById(int id);

	User save(UserRequestDTO user);
	
	User update(UserRequestDTO user);

	void delete(User user); // hard delete
}
