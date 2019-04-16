package com.cuong.controller.querycontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cuong.entity.Book;
import com.cuong.service.queryservice.IBookQueryService;

@RestController

public class BookQueryController {
	@Autowired
	private IBookQueryService bookQueryService;

	// get list books
	@GetMapping("/books")
	public ResponseEntity<Object> getAllBooks() {
		List<Book> book = bookQueryService.getAllBooks();
		return new ResponseEntity<Object>(book, HttpStatus.OK);
	}

	// get a book
	@GetMapping(value = "books/{bookId}")
	public ResponseEntity<Object> findBookById(@PathVariable("bookId") Integer bookId) {
		Book book = bookQueryService.getOne(bookId);
		return new ResponseEntity<Object>(book, HttpStatus.OK);
	}

	// get books by code
	@GetMapping(value = "/code/{code}")
	public ResponseEntity<Object> findBookByCode(@PathVariable("code") String code) {
		Book book = bookQueryService.findByCode(code);
		return new ResponseEntity<Object>(book, HttpStatus.OK);
	}

	// get books by name
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> findBookByName(@PathVariable("name") String name) {
		List<Book> books = bookQueryService.findByName(name);
		return new ResponseEntity<Object>(books, HttpStatus.OK);
	}

	// get books by category
	@GetMapping(value = "/category/{category}")
	public ResponseEntity<Object> findBookByCategory(@PathVariable("category") String category) {
		List<Book> books = bookQueryService.findByCategory(category);
		return new ResponseEntity<Object>(books, HttpStatus.OK);
	}

	// get books by author
	@GetMapping(value = "/author/{author}")
	public ResponseEntity<Object> findBookByAuthor(@PathVariable("author") String author) {
		List<Book> books = bookQueryService.findByAuthor(author);
		return new ResponseEntity<Object>(books, HttpStatus.OK);
	}

}
