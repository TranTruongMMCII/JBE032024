package com.r2s.java_backend_03.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_03.dto.request.UserRequestDTO;
import com.r2s.java_backend_03.dto.response.UserResponseDTO;
import com.r2s.java_backend_03.mapper.JBE03Mapper;
import com.r2s.java_backend_03.model.User;
import com.r2s.java_backend_03.model.response.SuccessResponse;
import com.r2s.java_backend_03.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	private final JBE03Mapper mapper;

	@GetMapping
//	@PreAuthorize(value = "hasRole('USER')")
//	@Secured(value = {"ROLE_USER"})
	public SuccessResponse<List<User>> getUsers(@RequestParam(name = "isDeleted", required = false) Boolean isDeleted,
			@RequestParam(name = "name", required = false) String name, Pageable pageable,
			@RequestParam(name = "sorts", required = false) String sorts) {
//		return this.userService.getUsers(isDeleted, name);

		System.err.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Sort sort = null;
		if (!StringUtils.isEmpty(sorts)) {
			String[] words = sorts.split(",");
			List<Order> orders = new ArrayList<>();
			for (int i = 0; i < words.length; i += 2) {
				orders.add(new Order(getDirection(words[i + 1]), words[i]));
			}

			if (!orders.isEmpty()) {
				sort = Sort.by(orders);
			}
		}

		PageRequest pageRequest;
		if (Objects.isNull(sort)) {
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		} else {
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		}
		return SuccessResponse.of(this.userService.getUsers(pageRequest));
	}

	private static Direction getDirection(String direction) {
		if ("DESC".equals(direction)) {
			return Direction.DESC;
		}

		return Direction.ASC;
	}

	@GetMapping(path = "/{id}")
	public SuccessResponse<UserResponseDTO> getUser(@PathVariable(name = "id") int id) {
//		return SuccessResponse.of(new UserResponseDTO(this.userService.getById(id)));
		return SuccessResponse.of(this.mapper.toResponse(this.userService.getById(id)));
	}

	@GetMapping(path = "byParams")
	public SuccessResponse<User> getUserByParam(@RequestParam(name = "id", defaultValue = "1") int id) {
		return SuccessResponse.of(this.userService.getById(id));
	}

	@PostMapping
	public SuccessResponse<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
		return SuccessResponse.of(this.mapper.toResponse(this.userService.save(userRequestDTO)));
	}

//	@PutMapping(path = "/{id}")
	@PutMapping
	public SuccessResponse<User> updateUser(@RequestBody UserRequestDTO user) {
		return SuccessResponse.of(this.userService.update(user));
	}

	@DeleteMapping
	public SuccessResponse<User> deleteUser(@RequestBody User user) {
		// tim user can xoa
		User foundUser = this.userService.getById(user.getId());

//		this.userService.delete(foundUser);
//		return user;

		// soft delete
		foundUser.setDeleted(true);
//		this.userService.save(foundUser);
		return SuccessResponse.of(foundUser);
	}
}
