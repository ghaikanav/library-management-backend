package com.sapient.pjp3.controllers;


import com.sapient.pjp3.dao.BookRequestsDao;
import com.sapient.pjp3.dao.BooksDao;
import com.sapient.pjp3.dao.ReviewsDao;
import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.entity.BookRequest;
import com.sapient.pjp3.entity.Review;
import com.sapient.pjp3.utils.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BooksController {
    BooksDao dao;
    BooksDao editBooksDao;
    BookRequestsDao dao_;
  
    @PostMapping("/request")
    public ResponseEntity<?> getOrdersForUser(
			@RequestHeader(name = "Authorization", required = false) String authHeader, @RequestBody BookRequest request) throws Exception {
    	Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {
			// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}
		
		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("totken = {}", token1);
			Integer userId1 = JwtUtil.verify(token1);
			
			log.info("THE returned", userId1);
			log.info(request.getTitle());
			Map<String, Object> map = new HashMap<>();
//			BOOK_TITLE, AUTHOR, REQUESTED_AT
			map.put("success", BookRequestsDao.create(userId1,request.getTitle(),request.getAuthor(),request.getRequestedAt()));
			map.put("user_id", userId1);
			return ResponseEntity.ok(map);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
	}
    
    @PostMapping("/{isbn}/reviews")
    public  ResponseEntity<?> getOrdersForUser(
			@RequestHeader(name = "Authorization", required = false) String authHeader,
			@PathVariable("isbn") long isbn,
			@RequestBody Review reviewRequest) {
    	Logger log = LoggerFactory.getLogger(BooksController.class);
    	log.info("authHeader = {}", authHeader);
    	
    	ReviewsDao reviewDao = new ReviewsDao();
    	
    	if(authHeader==null) {
			// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}
    	try {
			String token = authHeader.split(" ")[1]; // second element from the header's value
			log.info("totken = {}", token);
			Integer userId = JwtUtil.verify(token);
			
			reviewRequest.setReviewId(reviewDao.getMaxReviewID() + 1);
			reviewRequest.setUserId(userId);
			reviewRequest.setIsbn(isbn);
			
			log.info(reviewRequest.toString());
			
			if(reviewDao.addReview(reviewRequest)) {
				Map<String, Object> map = new HashMap<>();
				map.put("success", true);
				map.put("userId", userId);
				return ResponseEntity.ok(map);
			}
			else {
				Map<String, Object> map = new HashMap<>();
				map.put("success", false);
				map.put("userId", userId);
				return ResponseEntity.ok(map);
			}
			
			
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
		
    }

	@PutMapping("/{isbn}/reviews")
	public ResponseEntity<?> updateReview(
			@RequestHeader(name = "Authorization", required = false) String authHeader,
			@RequestBody Review review, @PathVariable int isbn
	) throws Exception {
		ReviewsDao reviewDao = new ReviewsDao();
		Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}

		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userId1 = JwtUtil.verify(token1);

			if(reviewDao.updateReview(review)) {return ResponseEntity.ok().body("Review Successfully Updated"); }
			else{ return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Review could not be updated"); }
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
	}


	@PutMapping("/{bookId}")
	public ResponseEntity<?> updateBook(
			@RequestHeader(name = "Authorization", required = false) String authHeader,
			@RequestBody Book book, @PathVariable int bookId
	) throws Exception {
		ReviewsDao reviewDao = new ReviewsDao();
		Logger log = LoggerFactory.getLogger(BooksController.class);
		log.info("authHeader = {}", authHeader);
		if(authHeader==null) {// Authorization header is missing
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is missing");
		}

		try {
			String token1 = authHeader.split(" ")[1]; // second element from the header's value
			log.info("token = {}", token1);
			Integer userId1 = JwtUtil.verify(token1);

			Book bookResp = editBooksDao.updateBooksDetails(
					book.getTitle(), book.getAuthor(), book.getPrice(), book.getRating(), book.getQuantity(), bookId);
			if(bookResp == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book details could not be updated");
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("isbn", bookResp.getIsbn()); // need to get this from DB using DAO
			map.put("Title", bookResp.getTitle());
			map.put("Author", bookResp.getAuthor());
			map.put("Price", bookResp.getPrice());
			map.put("Rating", bookResp.getRating());
			map.put("Quantity", bookResp.getQuantity());
			return ResponseEntity.ok(map);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization token is invalid or " + ex.getMessage());
		}
	}
}
