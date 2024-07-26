package com.r2s.java_backend_03.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.r2s.java_backend_03.model.User;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
//	

	@BeforeAll
	void beforeAll() {
		User user1 = new User(100, "Nguyen Van A", "a@gmail.com", false, null, null, null, null, null, null);
		User user2 = new User(101, "Tran Thi B", "b@gmail.com", true, null, null, null, null, null, null);

		this.userRepository.saveAll(List.of(user1, user2));
	}

	@AfterAll
	void afterAll() {
		List<User> users = this.userRepository.findByName("Nguyen Van A");
		users.addAll(this.userRepository.findByName("Tran Thi B"));

		this.userRepository.deleteAll(users);
	}

//	@BeforeEach
//	void beforeEach() {
////		System.out.println("beforeEach");
//		String a = "A";
//	}
//	
//	@AfterEach
//	void afterEach() {
//		System.out.println("afterEach");
//	}

	@Test
	void findByDeletedTest() {
		// given
		boolean isDeleted = true;

		// when
		List<User> foundUsers = this.userRepository.findByDeleted(isDeleted);

		// then
		assertNotNull(foundUsers);
		assertEquals(1, foundUsers.size());

		User foundUser = foundUsers.get(0);
		assertEquals("Tran Thi B", foundUser.getName());
		assertEquals("b@gmail.com", foundUser.getEmail());
	}
}
