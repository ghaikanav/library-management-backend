package com.sapient.pjp3.controllers;

import com.auth0.jwt.JWT;
import com.sapient.pjp3.entity.Login;
import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sapient.pjp3.dao.UsersDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	UsersDao dao;
	
	@GetMapping("/")
	public Optional<User> getUserInfo(@RequestBody JWT jwt) throws Exception {
		return dao.findById(JwtUtil.verify(jwt.toString()));
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login user) throws Exception {

		// TODO: make use of a DAO class to verify if the user's email and password are
		// valid
		// temporary fix; hard coded email/password verification

		if (user.getEmail().equals("vinod@vinod.co") && user.getPassword().equals("topsecret")) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1234); // need to get this from DB using DAO
			map.put("fullname", "Vinod Kumar Kayartaya");
			map.put("token", JwtUtil.createToken(1, "Vinod Kumar Kayartaya"));
			
			return ResponseEntity.ok(map);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
		}

	}
}
