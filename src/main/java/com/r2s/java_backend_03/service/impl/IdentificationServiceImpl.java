package com.r2s.java_backend_03.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.r2s.java_backend_03.model.Identification;
import com.r2s.java_backend_03.repository.IdentificationRepository;
import com.r2s.java_backend_03.service.IdentificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentificationServiceImpl implements IdentificationService {

	private final IdentificationRepository identificationRepository;

	@Override
	public Identification findById(int id) {
		Optional<Identification> optionalIdentification = this.identificationRepository.findById(id);

		if (optionalIdentification.isEmpty()) {
//			throw ....
			return null;
		}

		return optionalIdentification.get();
	}

}
