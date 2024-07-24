package com.r2s.java_backend_03.dto.request;

import lombok.Data;

@Data
public class SignUpRequest {
	private String name;
	private String userName;
	private String password;
}
