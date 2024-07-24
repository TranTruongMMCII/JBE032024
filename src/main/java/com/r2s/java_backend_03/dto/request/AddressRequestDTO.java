package com.r2s.java_backend_03.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

	private String province;

	private String district;

	private String commune;

	private String detail;

	private int userId;
}
