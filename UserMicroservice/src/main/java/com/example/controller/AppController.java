package com.example.controller;

import com.example.models.*;
import com.example.repo.UserTableRepo;
import com.example.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/authenticate")
public class AppController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserTableRepo repo;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			String token = userService.generateToken(loginRequest.getUsername());
			User user = repo.findByUsername(loginRequest.getUsername()).get();
			return ResponseEntity.ok().body(
					LoginResponse.builder().userLoginResponse(true).userId(user.getUserId()).token(token).build());
		} else
			return ResponseEntity.ok().body(LoginResponse.builder().userLoginResponse(false).build());
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		userService.validateToken(token);
		return "Token is valid";
	}

	@PostMapping("/registration")
	public ResponseEntity<Object> register(@RequestBody RegistrationRequest registerationRequest) {
		if (userService.addUserToSystem(User.builder()
				.userId(registerationRequest.getUserId())
				.username(registerationRequest.getUsername())
				.firstName(registerationRequest.getFirstName())
				.lastName(registerationRequest.getLastName())
				.nickName(registerationRequest.getNickName())
				.gender(registerationRequest.getGender())
				.userType(registerationRequest.getUserType())
				.password(registerationRequest.getPassword())
				.phoneNo(registerationRequest.getPhoneNo()).build())) {

			return ResponseEntity.ok().body(RegistrationResponse.builder().userRegistrationResponse(true).build());
		}

		return ResponseEntity.ok().body(RegistrationResponse.builder().userRegistrationResponse(false).build());
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<Object> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
		if (userService.forgetPassword(forgetPasswordRequest)) {
			return ResponseEntity.ok().body("Password Reset Successfully");
		}
		return ResponseEntity.ok().body("Try Again");
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.ok().body("User Deleted");
	}

}
