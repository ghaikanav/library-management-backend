package com.sapient.pjp3.controllers;

import com.auth0.jwt.JWT;
import com.sapient.pjp3.dao.BooksDao;
import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BooksController {
    BooksDao dao;

    @GetMapping("/")
    public List<Book> filterBooks(@RequestParam(defaultValue = "null") String genre,
                                  @RequestParam(defaultValue = "-1") float rating,
                                  @RequestParam(defaultValue = "null") String keyword,
                                  @RequestBody JWT jwt) throws Exception {
        List<Book> response = new ArrayList<>();
        if(genre != "null")
            response = dao.getBooksByGenre(genre);
        else if(rating != -1)
            response = dao.getBooksByRating(rating);
        else if(keyword != "null")
            response = dao.getBooksByKeyword("%"+keyword+"%");
        return response;
    }

}
