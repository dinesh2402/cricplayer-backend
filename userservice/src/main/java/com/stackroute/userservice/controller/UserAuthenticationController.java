package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*")
@RestController
public class UserAuthenticationController {
	
	private UserService authenticationService;
	private static final int EXPIRATION_TIME_IN_MINUTES = 720;

	@Autowired
	public UserAuthenticationController(UserService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws ServletException {
		user.setUserAddedDate(new Date());
		try {
			return new ResponseEntity<>(authenticationService.saveUser(user), HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> validateUser(@RequestBody User user) throws ServletException {
		try {
			Date expiresAt = DateUtils.addMinutes(new Date(), EXPIRATION_TIME_IN_MINUTES);
			authenticationService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
			Map<String, Object> data = new HashMap<>();
			data.put("userName", user.getUserName());
			data.put("token", generateJwtToken(user.getUserName(), user.getPassword(), expiresAt));
			data.put("expiresAt", expiresAt);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (UserNotFoundException e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	private String generateJwtToken(String userName, String password, Date expiresAt) {
		String jwtToken = Jwts.builder().setSubject(userName).setIssuedAt(new Date())
				.setExpiration(expiresAt)
				.signWith(SignatureAlgorithm.HS256, "secretSecurityKey").compact();
		return jwtToken;
	}

	

}
