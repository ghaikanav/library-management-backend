package com.sapient.pjp3.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.pjp3.dao.UsersDao;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	UsersDao dao = new UsersDao();
	
	@GetMapping("/welcome")
	public String welcomePage() {
		return "Welcome to this spring boot application";
	}

}
