package com.r2s.java_backend_03.dto.response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.java_backend_03.model.Identification;
import com.r2s.java_backend_03.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
	private int id;
	private String name;
	private String email;
	private List<AddressResponseDTO> addresses;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private Date expiredDate;

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();

		Identification identification = user.getIdentification();
		if (Objects.nonNull(identification)) {
			this.expiredDate = identification.getExpiredDate();
		}
	}
}
