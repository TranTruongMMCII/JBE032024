package com.r2s.java_backend_03.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.r2s.java_backend_03.exception.ValidationException;
import com.r2s.java_backend_03.model.User;

public class UserValidatorTest {

	@Test
	@DisplayName("validateSavedUser with null user")
	void validateSavedUserThrowException() {
		// kiem tra user is null
		// runnale ()->
		assertThrows(ValidationException.class, () -> UserValidator.validateSavedUser(null));

		// ....
		assertThrows(ValidationException.class, () -> UserValidator.validateSavedUser(new User()));
	}

	@Test
	@DisplayName("validateSavedUser with user blank name")
	void validateSavedUserThrowException1() {
		assertThrows(ValidationException.class, () -> UserValidator.validateSavedUser(new User()));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
	@DisplayName("assert equals")
	void test(int a) {
		assertEquals(a, a);
		assertTrue(a >= a);
	}
}
