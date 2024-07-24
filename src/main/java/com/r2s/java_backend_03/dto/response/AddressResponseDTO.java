package com.r2s.java_backend_03.dto.response;

import com.r2s.java_backend_03.dto.request.AddressRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
	private int id;

	private String province;

	private String district;

	private String commune;

	private String detail;
}
