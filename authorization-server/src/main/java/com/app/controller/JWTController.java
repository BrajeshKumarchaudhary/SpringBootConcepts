package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.JWTRequest;
import com.app.model.JWTResponse;
import com.app.service.CustomUserDetailService;
import com.app.util.JWtHelper;

@RestController
public class JWTController {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JWtHelper jwtHelper;

	@Autowired
	private AuthenticationManager authenticationManger;

	@PostMapping(value = "/token")
	public ResponseEntity<JWTResponse> generateToken(@RequestBody JWTRequest request) throws Exception {
		System.out.println(request);
		try {
			// verify userName and password
			authenticationManger.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getUserPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Bad UserName");
		}
		// getUserDetails
		UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUserName());
		String token = jwtHelper.generateToken(userDetails);
		return new ResponseEntity<JWTResponse>(new JWTResponse(token), HttpStatus.CREATED);
	}
}
