package com.r2s.java_backend_03.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.r2s.java_backend_03.model.User;
import com.r2s.java_backend_03.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	void getUsersByIsDeleted() {
		// given
		boolean isDeleted = true;
		User user = new User(101, "Tran Thi B", "b@gmail.com", true, null, null, null, null, null, null);

		// when
		when(this.userRepository.findByDeleted(eq(true))).thenReturn(List.of(user));
		List<User> givenUsers = this.userService.getUsers(isDeleted);

		// then
		assertNotNull(givenUsers);
		assertEquals(1, givenUsers.size());

		User expectedUser = new User(101, "Tran Thi B", "b@gmail.com", true, null, null, null, null, null, null);
		assertEquals(expectedUser.getName(), givenUsers.get(0).getName());
		verify(this.userRepository, times(1)).findByDeleted(eq(true));
	}
}
