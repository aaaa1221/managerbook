package com.cuong.service.commandservice.commandserviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.entity.Book;
import com.cuong.errorhandling.BookNotFoundException;
import com.cuong.repository.commandrepository.IBookCommandRepository;
import com.cuong.service.commandservice.IBookCommandService;
import com.cuong.util.ValidParam;

@Service
public class BookCommandServiceImpl implements IBookCommandService {

	@Autowired
	private IBookCommandRepository bookCommandrepository;

	@Override
	public Book createBook(Book bookRequest) {
		ValidParam.checkUniqueBookId(bookCommandrepository.getListUid(), bookRequest.getUid());
		ValidParam.checkUniqueCode(bookCommandrepository.getListCode(), bookRequest.getCode());
		ValidParam.validBookId(bookRequest.getUid());

		return bookCommandrepository.save(bookRequest);

	}

	@Override
	public Book updateBook(Integer bookId, Book bookRequest) {
		{
			Book book = bookCommandrepository.findByUid(bookId);
			if (book == null) {
				throw new BookNotFoundException("book is not exist");
			}

			ValidParam.checkSizeParamOfBook(book);
			ValidParam.checkUniqueCode(bookCommandrepository.getListCode(), book.getCode());
			return bookCommandrepository.save(book);
		}
	}

	@Override
	public Boolean deleteBook(Integer bookId, Boolean deleteBook) {
		Book book = bookCommandrepository.findByUid(bookId);
		if (book == null) {
			throw new BookNotFoundException("book is not exist");
		}
		bookCommandrepository.delete(book);
		return true;

	}

	@Override
	public Book updateBookbyCode(String code, Book bookRequest) {
		Book book = bookCommandrepository.findByCode(code);
		if (book == null) {
			throw new BookNotFoundException("book is not exist");
		}
//		ValidParam.checkSizeParamOfBook(book);
//		ValidParam.checkUniqueCode(bookCommandrepository.getListCode(), book.getCode());
		return bookCommandrepository.save(book);
	}

	@Override
	public Boolean deleteBookByCode(String code, Boolean deleteBook) {
		Book book = bookCommandrepository.findByCode(code);
		if (book == null) {
			throw new BookNotFoundException("book is not exist");
		}
		bookCommandrepository.delete(book);
		return true;
	}

}
