package com.r2s.java_backend_03.model.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
	private final String action = "FAILURE";
	private int status;
	private String code;
	private String message;
	private Map<String, Object> details;

	public static ErrorResponse of(int status, String code, String message) {
		return ErrorResponse.builder()
				.status(status)
				.code(code)
				.message(message)
				.build();
	}
}
