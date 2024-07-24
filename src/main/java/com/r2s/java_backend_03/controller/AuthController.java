package com.r2s.java_backend_03.controller;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_03.dto.request.SignInRequest;
import com.r2s.java_backend_03.dto.request.SignUpRequest;
import com.r2s.java_backend_03.dto.request.UserRequestDTO;
import com.r2s.java_backend_03.dto.response.SignInResponse;
import com.r2s.java_backend_03.mapper.JBE03Mapper;
import com.r2s.java_backend_03.model.User;
import com.r2s.java_backend_03.model.response.SuccessResponse;
import com.r2s.java_backend_03.security.CustomUserDetails;
import com.r2s.java_backend_03.service.UserService;
import com.r2s.java_backend_03.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JBE03Mapper mapper;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@PostMapping
	public SuccessResponse<Boolean> signUp(@RequestBody SignUpRequest request) {
		UserRequestDTO user = this.mapper.toDTO(request);

		User response = this.userService.save(user);
		return SuccessResponse.of(response != null);
	}

	@PostMapping(path = "/signIn")
	public SuccessResponse<SignInResponse> signIn(@RequestBody SignInRequest request) {
		Authentication authentication = 
				this.authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        // Retrieve user details from the authenticated token
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Generate JWT token
        String accessToken = jwtUtils.generateToken(userDetails);
        Date expriedDate = jwtUtils.extractExpiration(accessToken);

        return SuccessResponse.of(SignInResponse.builder()
        		.token(accessToken)
        		.expiredDate(expriedDate)
        		.build());
	}
}
