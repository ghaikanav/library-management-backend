package com.sapient.pjp3.controllers;

import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sapient.pjp3.dao.BookRequestsDao;
import com.sapient.pjp3.dao.BooksDao;
import com.sapient.pjp3.dao.UsersDao;

import java.util.HashMap;
import java.util.Map;



@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	UsersDao usersDao = new UsersDao();
	BooksDao booksDao = new BooksDao();
	
	@GetMapping
	public ResponseEntity<?> getUser(@RequestHeader(name = "Authorization", required = false) String authHeader) {
		Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {
			// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}
		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userId = JwtUtil.verify(token1);
			
			log.info("THE returned", userId);
			Map<String, Object> map = new HashMap<>();
			map.put("success", usersDao.getUser(userId));//lets see
			map.put("user_id", userId);
			return ResponseEntity.ok(map);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
		
	}
	
	@GetMapping("/borrowedBooks")
	public ResponseEntity<?> getBorrowedBooks(@RequestHeader(name = "Authorization", required = false) String authHeader) {
		Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {
			// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}
		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userId = JwtUtil.verify(token1);
			
			log.info("THE returned", userId);
			Map<String, Object> map = new HashMap<>();
			map.put("current", booksDao.getCurrentBooks(userId));
			map.put("previous", booksDao.getPreviousBooks(userId));
			map.put("user_id", userId);
			return ResponseEntity.ok(map);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
		
	}
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
	public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {
		int check = usersDao.addUser(user);
		
		if(check != -1) {
			Map<String, Object> map = new HashMap<>();
			User user1 = usersDao.getUserByEmail(user.getEmail());
			map.put("success", user1);//lets see
			map.put("user_id", user1.getId());
			map.put("token", JwtUtil.createToken(user1.getId(),user1.getFullname()));
			
			return ResponseEntity.ok(map);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account already exists!!");
	}
	
	@GetMapping("/payFine")
	public ResponseEntity<?> payFine(@RequestHeader(name = "Authorization", required = false) String authHeader){
		Logger log = LoggerFactory.getLogger(UsersController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {
			// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}
		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userId = JwtUtil.verify(token1);
			
			log.info("THE returned", userId);
			Map<String, Object> map = new HashMap<>();
			map.put("success", usersDao.payFine(userId));//lets see
			map.put("user_id", userId);
			return ResponseEntity.ok(map);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
		
		
	}
}
