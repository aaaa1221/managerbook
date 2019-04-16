package com.cuong.junit.service.query;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuong.entity.Book;
import com.cuong.errorhandling.BookNotFoundException;
import com.cuong.errorhandling.ValidationException;
import com.cuong.repository.querycontroller.IBookQueryRepository;
import com.cuong.service.queryservice.queryserviceimpl.BookQueryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class QueryTest {

	@Mock
	private IBookQueryRepository bookQueryRepository;

	@InjectMocks
	private BookQueryServiceImpl bookQueryServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllBook() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findAll()).thenReturn(bookList);

		List<Book> result = bookQueryServiceImpl.getAllBooks();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetAllBookError() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findAll()).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.getAllBooks());
		assertEquals(exception.getMessage(), "no list");
	}

	@Test
	public void testGetBookByIdError() {
		@SuppressWarnings("unused")
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookQueryRepository.findByUid(99)).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.getOne(99));
		assertEquals(exception.getMessage(), "book is not exist");

	}

	@Test
	public void testGetBookByIdError1() {
		Book book = new Book(-1, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookQueryRepository.findByUid(99)).thenReturn(book);
		ValidationException exception = assertThrows(ValidationException.class, () -> bookQueryServiceImpl.getOne(-1));
		assertEquals(exception.getMessage(), "Invalid Book ID");

	}

	@Test
	public void testGetBookById() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookQueryRepository.findByUid(99)).thenReturn(book);

		Book result = bookQueryServiceImpl.getOne(99);
		assertEquals(99, result.getUid());
		assertEquals("b123", result.getCode());
	}

	@Test
	public void testGetBookByCategory() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findByCategory("AAA")).thenReturn(bookList);

		List<Book> result = bookQueryServiceImpl.findByCategory("AAA");
		assertEquals(3, result.size());
	}

	@Test
	public void testGetBookByCategoryError() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findByCategory("AAA")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByCategory("AAA"));
		assertEquals(exception.getMessage(), "Book with category is  Not Found");

	}

	@Test
	public void testGetBookByName() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "vc234", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "vc234", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "vc234", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findByName("vc234")).thenReturn(bookList);

		List<Book> result = bookQueryServiceImpl.findByName("vc234");
		assertEquals(3, result.size());
	}

	@Test
	public void testGetBookByNameError() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "vc234", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "vc234", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "vc234", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findByName("vc234")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByName("vc234"));
		assertEquals(exception.getMessage(), "Book with name is  Not Found");

	}

	@Test
	public void testGetBookByAuthor() {

		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findByAuthor("VanCuong")).thenReturn(bookList);

		List<Book> result = bookQueryServiceImpl.findByAuthor("VanCuong");
		assertEquals(3, result.size());
	}

	@Test
	public void testGetBookByAuthorError() {

		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(100, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		bookList.add(new Book(101, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2"));
		when(bookQueryRepository.findByAuthor("VanCuong")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByAuthor("VanCuong"));
		assertEquals(exception.getMessage(), "Book with author is  Not Found");
	}

	@Test
	public void testGetBookByCode() {
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookQueryRepository.findByCode("b123")).thenReturn(book);

		Book result = bookQueryServiceImpl.findByCode("b123");
		assertEquals("b123", result.getCode());
		assertEquals("văn cường", result.getName());
	}

	@Test
	public void testGetBookByCodeError() {
		@SuppressWarnings("unused")
		Book book = new Book(99, "b123", "văn cường", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
				"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
		when(bookQueryRepository.findByCode("b123")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByCode("b123"));
		assertEquals(exception.getMessage(), "Book with code is Not Found");

	}
}
