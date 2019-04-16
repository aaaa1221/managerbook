package com.cuong.service.commandservice;

import com.cuong.entity.Book;

public interface IBookCommandService {
	Book createBook(Book bookRequest);

	Book updateBook(Integer bookId, Book bookRequest);

	Book updateBookbyCode(String code, Book bookRequest);

	Boolean deleteBook(Integer bookId, Boolean deleteBook);

	Boolean deleteBookByCode(String code, Boolean deleteBook);
}
