package com.elasticsearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.model.Book;
import com.elasticsearch.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/book/dummy")
	public @ResponseBody Book getDummyBook() {
		Book book = new Book();
		book.setId("10");
		book.setAuthor("Packt Publication");
		book.setTitle("OCAJP By Mala Gupta");
		book.setReleaseDate("01-09-2001");
		return book;
	} 
	
	@GetMapping("/book/{id}")
	public @ResponseBody Book getBook(@PathVariable String id) {
		Book book = bookService.findOne(id);
		return book;
	}
	
//	here doing something new
	
	/*@GetMapping("/book/{title}")
	public @ResponseBody Book getBook(@PathVariable String title) {
		Book book = (Book)bookService.findByTitle(title);
		return book;
	}*/
	
	@PostMapping("/book")
	public @ResponseBody Book saveBook(@RequestBody Book book) {
		Book updateBook = bookService.save(book);
		return updateBook;
	}
	
	@DeleteMapping("/book")
	public HttpStatus deleteBook(@RequestBody Book book) {
		bookService.delete(book);
		return HttpStatus.ACCEPTED;
	}
	
	@GetMapping("/books")
	@ResponseBody
	public List<Book> getBooks() {
		Iterable<Book> source = bookService.findAll();
		List<Book> target = new ArrayList<>();
		source.forEach(target::add);
		return target;
	}
}
