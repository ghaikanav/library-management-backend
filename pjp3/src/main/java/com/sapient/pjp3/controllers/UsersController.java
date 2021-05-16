package com.sapient.pjp3.controllers;

import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sapient.pjp3.dao.UsersDao;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	UsersDao usersDao = new UsersDao();
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws Exception {
		
		User user1 = usersDao.getUserByEmail(user.getEmail());
		if (user.getPassword().equals(user1.getPassword())) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", user1.getId()); // need to get this from DB using DAO
			map.put("Full Name", user1.getFullname());
			map.put("token", JwtUtil.createToken(user1.getId(),user1.getFullname()));
			
			return ResponseEntity.ok(map);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
		}


	}
	
	@PostMapping("/register")
	public User add(@RequestBody User user) {
		usersDao.add(user);
		return user;
	}

}
