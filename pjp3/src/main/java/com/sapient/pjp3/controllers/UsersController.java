package com.sapient.pjp3.controllers;

import com.sapient.pjp3.entity.Login;
import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sapient.pjp3.dao.LoginDao;
import com.sapient.pjp3.dao.UsersDao;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	UsersDao usersDao;
	LoginDao loginDao;
	
//	@GetMapping("/")
//	public Optional<User> getUserInfo(@RequestBody JWT jwt) throws Exception {
//		return dao.findById(JwtUtil.verify(jwt.toString()));
//	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login user) throws Exception {

		// TODO: make use of a DAO class to verify if the user's email and password are
		// valid
		// temporary fix; hard coded email/password verification
	
//			map.put("id", 1234); // need to get this from DB using DAO
//			
		Login login = LoginDao.getLogin(user.getEmail());
		if (user.getPassword().equals(login.getPassword())) {
			Map<String, Object> map = new HashMap<>();
			User user1 = UsersDao.getUser(login.getId());
			map.put("id", login.getId()); // need to get this from DB using DAO
			map.put("Full Name", user1.getFullname());
			map.put("token", JwtUtil.createToken(login.getId(),user1.getFullname()));
			
			return ResponseEntity.ok(map);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
		}


	}
	
	@PostMapping("/api/register")
	public User add(@RequestBody User user) {
		usersDao.add(user);
		return user;
	}
	
	public Login add(@RequestBody Login login) {
		loginDao.add(login);
		return login;
	}

	@PutMapping("/edit/{userId}")
	public ResponseEntity<?> updateReview(
			@RequestHeader(name = "Authorization", required = false) String authHeader,
			@RequestBody String fullName, @RequestBody String emailId, @PathVariable int userId
	) throws Exception {
		UsersDao usersDao = new UsersDao();
		Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}

		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userIdJWT = JwtUtil.verify(token1);

			if(userIdJWT == userId) {
				User userResp = usersDao.updateUserName(fullName, userId);
				Login loginResp = loginDao.updateLogin(emailId, userId);

				if(userResp != null && loginResp != null){
					Map<String, Object> map = new HashMap<>();
					map.put("id", userResp.getId()); // need to get this from DB using DAO
					map.put("Full Name", userResp.getFullname());
					map.put("Email", loginResp.getEmail());
					return ResponseEntity.ok(map);
				}
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User details could not be updated");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
	}


	@PutMapping("/{id}/dues")
	public ResponseEntity<?> updateReview(
			@RequestHeader(name = "Authorization", required = false) String authHeader,
			@PathVariable Integer userId
	) throws Exception {
		UsersDao usersDao = new UsersDao();
		Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}

		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userIdJWT = JwtUtil.verify(token1);

			User userResp = usersDao.updateDueAmount(userId, 400);
			if(userResp == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fines could not be calculated");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("id", userResp.getId()); // need to get this from DB using DAO
			map.put("Full Name", userResp.getFullname());
			map.put("Fine", userResp.getFine());
			return ResponseEntity.ok(map);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
	}
}
