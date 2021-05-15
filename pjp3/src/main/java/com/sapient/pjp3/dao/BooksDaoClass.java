package com.sapient.pjp3.dao;

import java.util.List;
import java.util.Optional;

import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.entity.Review;

public class BooksDaoClass implements BooksDao {

	@Override
	public <S extends Book> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Book> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Book entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Book> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getBooksByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByRating(float rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
