package com.r2s.java_backend_03.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
	private int id;
	private String name;
	private String email;
	private String userName;
	private String password;
	private Date expiredDate;
}
