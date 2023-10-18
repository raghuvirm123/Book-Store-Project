package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	 private BookRepository repo;
	
	public void saveb(Book b)
	{
		 repo.save(b);
	}
	
	public List<Book> getAllBook()
	{
		return repo.findAll();
	}
	
	public Book gteBookById(int id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteBook(int id)
	{
		repo.deleteById(id);
	}
}
