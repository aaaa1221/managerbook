package com.cuong.junit.controller.querycontroller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cuong.controller.querycontroller.BookQueryController;
import com.cuong.entity.Book;
import com.cuong.errorhandling.BookNotFoundException;
import com.cuong.repository.querycontroller.IBookQueryRepository;
import com.cuong.service.queryservice.IBookQueryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class QueryControllerTest {

	private MockMvc mmvc;

	@Mock
	private IBookQueryRepository bookQueryRepository;

	@Mock
	private IBookQueryService bookQueryService;

	@InjectMocks
	private BookQueryController bookQueryController;

	public List<Book> booksCasePass = new ArrayList<Book>();
	public List<Book> booksCaseFail = new ArrayList<Book>();
	Book book1 = new Book(99, "b123", "vancuong", "giọt lệ lung vancuong", "AAA", "VanCuong", "mùa xuân nóng bỏng",
			"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");
	Book book2 = new Book(100, "b123", "vancuong", "giọt lệ lung vancuong", "AAA", "VanCuong", "mùa xuân nóng bỏng",
			"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");

	@BeforeEach
	public void init() {
		booksCasePass.add(book1);
		booksCasePass.add(book2);
		mmvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();

	}

	@Test
	public void testGetAllBooksError() throws Exception {
		Mockito.when(bookQueryService.getAllBooks()).thenThrow(new BookNotFoundException("Don't have any book"));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryController.getAllBooks());
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testFindBookByIdCaseError() {
		Mockito.when(bookQueryService.getOne(1)).thenThrow(new BookNotFoundException("Don't have any book"));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryController.findBookById(1));
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testFindBookByCodeError() {
		Mockito.when(bookQueryService.findByCode("1")).thenThrow(new BookNotFoundException("Don't have any book"));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryController.findBookByCode("1"));
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testFindBookByNameError() {
		Mockito.when(bookQueryService.findByName("vancuong"))
				.thenThrow(new BookNotFoundException("Don't have any book"));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryController.findBookByName("vancuong"));
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testFindBookByAuthorError() {
		Mockito.when(bookQueryService.findByAuthor("1")).thenThrow(new BookNotFoundException("Don't have any book"));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryController.findBookByAuthor("1"));
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testFindBookByCategoryError() {
		Mockito.when(bookQueryService.findByCategory("1")).thenThrow(new BookNotFoundException("Don't have any book"));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryController.findBookByCategory("1"));
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testGetAllBooksOk() throws Exception {
		Mockito.when(bookQueryService.getAllBooks()).thenReturn(booksCasePass);
		mmvc.perform(get("http://localhost:8080/books").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("vancuong")));

	}

	@Test
	public void testFindBookByIdOk() throws Exception {
		Mockito.when(bookQueryService.getOne(1)).thenReturn(book1);
		mmvc.perform(get("http://localhost:8080/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("code", is("b123")));
	}

	@Test
	public void testFindBookByCodeOk() throws Exception {
		Mockito.when(bookQueryService.findByCode("b123")).thenReturn(book1);
		mmvc.perform(get("http://localhost:8080/code/b123").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("name", is("vancuong")));
	}

	@Test
	public void testFindBookByNameOk() throws Exception {
		Mockito.when(bookQueryService.findByName("vancuong")).thenReturn(booksCasePass);
		mmvc.perform(get("http://localhost:8080/name/vancuong").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("vancuong")));
	}

	@Test
	public void testFindBookByCategoryOk() throws Exception {
		Mockito.when(bookQueryService.findByCategory("1")).thenReturn(booksCasePass);
		mmvc.perform(get("http://localhost:8080/category/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("vancuong")));
	}

	@Test
	public void testFindBookByAuthorOk() throws Exception {
		Mockito.when(bookQueryService.findByAuthor("1")).thenReturn(booksCasePass);
		mmvc.perform(get("http://localhost:8080/author/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("vancuong")));
	}

}
