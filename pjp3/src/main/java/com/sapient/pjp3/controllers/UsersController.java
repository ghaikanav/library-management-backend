package com.sapient.pjp3.controllers;

import com.auth0.jwt.JWT;
import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import com.sapient.pjp3.dao.UsersDao;

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

}
