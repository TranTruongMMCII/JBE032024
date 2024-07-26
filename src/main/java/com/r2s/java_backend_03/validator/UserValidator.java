package com.r2s.java_backend_03.validator;

import java.util.Objects;

import org.springframework.util.StringUtils;

import com.r2s.java_backend_03.exception.ValidationException;
import com.r2s.java_backend_03.model.User;

public class UserValidator {

	public static void validateSavedUser(User user) {
		if (Objects.isNull(user)) {
			throw new ValidationException("NotNull.user", "User is required!");
		}

		if (StringUtils.isEmpty(user.getName()) || user.getName().isBlank()) {
			throw new ValidationException("NotBlank.user.name", "User.Name is not blank!");
		}

		if (StringUtils.isEmpty(user.getEmail()) || user.getEmail().isBlank()) {
			throw new ValidationException("NotBlank.user.email", "User.Email is not blank!");
		}
	}

	public static void validateUpdatedUser(User user) {
		if (Objects.isNull(user)) {
			throw new ValidationException("NotNull.user", "User is required!");
		}

		if (!StringUtils.isEmpty(user.getName()) && user.getName().isBlank()) {
			throw new ValidationException("NotBlank.user.name", "User.Name is not blank!");
		}

		if (!StringUtils.isEmpty(user.getEmail()) && user.getEmail().isBlank()) {
			throw new ValidationException("NotBlank.user.email", "User.Email is not blank!");
		}
	}
}
