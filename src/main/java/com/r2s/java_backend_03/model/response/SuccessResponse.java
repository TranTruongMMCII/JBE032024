package com.r2s.java_backend_03.model.response;

import java.util.Collection;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuccessResponse<T> {
	private final String action = "SUCCESS";
	private int status;
	private T data;
	private int size;
	private String message;

	@SuppressWarnings("rawtypes")
	public static <T> SuccessResponse<T> of(T data) {
		int size = 0;
		if (!Objects.isNull(data) && data instanceof Collection<?>) {
			size = ((Collection) data).size();
		}

		return SuccessResponse.<T>builder()
				.status(HttpStatus.OK.value())
				.data(data)
				.size(size)
				.message("SUCCESS")
				.build();
	}
}
