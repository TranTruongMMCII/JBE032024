package com.r2s.java_backend_03.mapper;

import java.util.Date;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.r2s.java_backend_03.dto.request.AddressRequestDTO;
import com.r2s.java_backend_03.dto.request.SignUpRequest;
import com.r2s.java_backend_03.dto.request.UserRequestDTO;
import com.r2s.java_backend_03.dto.response.AddressResponseDTO;
import com.r2s.java_backend_03.dto.response.UserResponseDTO;
import com.r2s.java_backend_03.model.Address;
import com.r2s.java_backend_03.model.Identification;
import com.r2s.java_backend_03.model.User;

@Mapper(componentModel = "spring")
public interface JBE03Mapper {

	@Mapping(target = "expiredDate", expression = "java(mapExpiredDate(user.getIdentification()))")
	UserResponseDTO toResponse(User user);

	default Date mapExpiredDate(Identification identification) {
		if (Objects.isNull(identification)) {
			return null;
		}

		return identification.getExpiredDate();
	}

	User toModel(UserRequestDTO userRequestDTO);

	@Mapping(target = "user.id", source = "address.userId")
	Address toModel(AddressRequestDTO address);
	
	AddressResponseDTO toResponse(Address address);
	
	UserRequestDTO toDTO(SignUpRequest request);
}
