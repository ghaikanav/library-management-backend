package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BooksDao extends CrudRepository<Book,Long> {

    @Query("select * from books where genre = :genre")
    public List<Book> getBooksByGenre(@Param("genre") String genre);

    @Query("select * from books where rating >= :rating")
    public List<Book> getBooksByRating(@Param("rating")float rating);

    @Query("select * from books where lower(title) like lower(:keyword)")
    public List<Book> getBooksByKeyword(@Param("keyword") String keyword);

}
