package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;

import ch.qos.logback.core.model.Model;

@Controller
public class bookController {

	@Autowired 
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister()
	{
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBooks()
	{
		List<Book> b=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList", "book", b);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b)
	{
		service.saveb(b);
		return "redirect:/available_books";
	}
	
	
	
	@GetMapping("/my_books")
	public String getMyBooks(org.springframework.ui.Model model )
	{
		List<MyBookList> list=myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		Book b=service.gteBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/bookDelete/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		service.deleteBook(id);
		return "redirect:/available_books";
	}
	
	@RequestMapping("/bookEdit/{id}")
	public String editBook(@PathVariable("id") int id, org.springframework.ui.Model model)
	{
		Book b=service.gteBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}
}
