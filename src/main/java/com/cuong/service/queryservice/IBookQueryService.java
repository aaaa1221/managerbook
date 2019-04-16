package com.cuong.service.queryservice;

import java.util.List;

import com.cuong.entity.Book;

public interface IBookQueryService {
	List<Book> getAllBooks();

	Book getOne(int uid);

	Book findByCode(String code);

	List<Book> findByName(String name);

	List<Book> findByCategory(String category);

	List<Book> findByAuthor(String author);
}
