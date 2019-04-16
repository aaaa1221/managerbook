package com.cuong.junit.service.command;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cuong.entity.Book;
import com.cuong.errorhandling.BookNotFoundException;
import com.cuong.repository.commandrepository.IBookCommandRepository;
import com.cuong.repository.querycontroller.IBookQueryRepository;
import com.cuong.service.commandservice.commandserviceimpl.BookCommandServiceImpl;

@SpringBootTest()
public class CommandTest {

	@Mock
	IBookQueryRepository bookQueryRepository;

	@Mock
	IBookCommandRepository bookCommandRepository;

	@InjectMocks
	BookCommandServiceImpl bookCommandServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this); // without this you will get NPE
	}

	@Test
	public void createBookOk() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		bookCommandServiceImpl.createBook(book);
		verify(bookCommandRepository, times(1)).save(book);

	}

	@Test
	public void deleteBookByIdOk() {

		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByUid(99)).thenReturn(book);
		assertEquals(bookCommandServiceImpl.deleteBook(99, true), true);
	}

	@Test
	public void deleteBookByCodeOk() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByCode("b123")).thenReturn(book);
		assertEquals(bookCommandServiceImpl.deleteBookByCode("b123", true), true);
	}

	@Test
	public void deleteBookByIdError() {
		@SuppressWarnings("unused")
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByUid(99)).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookCommandServiceImpl.deleteBook(99, true));
		assertEquals(exception.getMessage(), "book is not exist");
	}

	@Test
	public void deleteBookByCodeError() {
		@SuppressWarnings("unused")
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByCode("b123")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookCommandServiceImpl.deleteBookByCode("b123", true));
		assertEquals(exception.getMessage(), "book is not exist");
	}

	@Test
	public void updateBookByIdOk() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByUid(99)).thenReturn(book);
		when(bookCommandRepository.save(book)).thenReturn(book);
		assertEquals(bookCommandServiceImpl.updateBook(99, book), book);
	}

	@Test
	public void updateBookByCodeOk() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByCode("b123")).thenReturn(book);
		when(bookCommandRepository.save(book)).thenReturn(book);
		assertEquals(bookCommandServiceImpl.updateBookbyCode("b123", book), book);
	}

	@Test
	public void updateBookByIdError() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByUid(99)).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookCommandServiceImpl.updateBook(99, book));
		assertEquals(exception.getMessage(), "book is not exist");
	}

	@Test
	public void updateBookByCodeError() {
		@SuppressWarnings("unused")
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookCommandRepository.findByCode("b123")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookCommandServiceImpl.updateBookbyCode("b123", null));
		assertEquals(exception.getMessage(), "book is not exist");
	}

}
