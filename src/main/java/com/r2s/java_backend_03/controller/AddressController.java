package com.r2s.java_backend_03.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_03.dto.request.AddressRequestDTO;
import com.r2s.java_backend_03.dto.response.AddressResponseDTO;
import com.r2s.java_backend_03.mapper.JBE03Mapper;
import com.r2s.java_backend_03.model.Address;
import com.r2s.java_backend_03.model.response.SuccessResponse;
import com.r2s.java_backend_03.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/addresses")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;

	private final JBE03Mapper mapper;

	@PostMapping
	public SuccessResponse<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO dto) {
		Address address = this.mapper.toModel(dto);

		return SuccessResponse.of(this.mapper.toResponse(this.addressService.save(address)));
	}
}
