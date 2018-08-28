package com.mkomo.jorts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkomo.jorts.exception.ResourceNotFoundException;
import com.mkomo.jorts.model.User;
import com.mkomo.jorts.payload.UserIdentityAvailability;
import com.mkomo.jorts.repository.UserRepository;
import com.mkomo.jorts.security.CurrentUser;
import com.mkomo.jorts.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserPrincipal getCurrentUser(@CurrentUser UserPrincipal user) {
		return user;
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/users/{username}")
	public UserPrincipal getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		return UserPrincipal.create(user);
	}

}
