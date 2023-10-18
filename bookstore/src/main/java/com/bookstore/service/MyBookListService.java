package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.MyBookList;
import com.bookstore.repository.MyBookRepository;

@Service
public class MyBookListService {

	@Autowired
	private MyBookRepository repo;
	
	public void saveMyBooks(MyBookList book)
	{
		repo.save(book);
	}
	
	public List<MyBookList> getAllMyBooks()
	{
		return repo.findAll();
	}
	
	public void deleteById(int id)
	{
		repo.deleteById(id);
	}
}
