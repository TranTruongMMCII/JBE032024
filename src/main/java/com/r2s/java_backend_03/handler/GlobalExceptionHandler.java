package com.r2s.java_backend_03.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.r2s.java_backend_03.exception.UserNotFoundException;
import com.r2s.java_backend_03.exception.ValidationException;
import com.r2s.java_backend_03.model.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { ValidationException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse validationExceptionHandler(ValidationException e) {
		return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), e.getCode(), e.getMessage());
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException e) {
		return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "NotFound.user", "User is not found!");
	}
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception e) {
		return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, e.getMessage());
	}
}
