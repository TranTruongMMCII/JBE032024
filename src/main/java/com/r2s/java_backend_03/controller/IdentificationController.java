package com.r2s.java_backend_03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_03.model.Identification;
import com.r2s.java_backend_03.model.response.SuccessResponse;
import com.r2s.java_backend_03.service.IdentificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/identifications")
@RequiredArgsConstructor
public class IdentificationController {

	private final IdentificationService identificationService;

	@GetMapping
	public SuccessResponse<Identification> getIdentification(@RequestParam(name = "id", required = true) int id) {
		return SuccessResponse.of(this.identificationService.findById(id));
	}
}
