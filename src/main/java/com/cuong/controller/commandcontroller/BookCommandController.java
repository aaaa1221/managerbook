package com.cuong.controller.commandcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cuong.entity.Book;
import com.cuong.service.commandservice.IBookCommandService;


@Controller
@RequestMapping("/books")
public class BookCommandController {
	@Autowired
	private IBookCommandService bookCommandService;
	//create book
	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book bookRequest) {
		return new ResponseEntity<>(bookCommandService.createBook(bookRequest), HttpStatus.CREATED);
	}
	// update book
	@PutMapping("edit/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @Valid @RequestBody Book bookRequest) {
		return new ResponseEntity<>(bookCommandService.updateBook(bookId, bookRequest), HttpStatus.OK);
	}
	// update book
	@PutMapping("editbycode/{code}")
	public ResponseEntity<Book> updateBookByCode(@PathVariable String code, @Valid @RequestBody Book bookRequest) {
		return new ResponseEntity<>(bookCommandService.updateBookbyCode(code, bookRequest), HttpStatus.OK);
	}
	//delete book by id
	@DeleteMapping("delete/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
		bookCommandService.deleteBook(bookId, true);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//delete book by code
	@DeleteMapping("deletebycode/{code}")
	public ResponseEntity<Void> deleteBookByCode(@PathVariable String code) {
		bookCommandService.deleteBookByCode(code, true);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
