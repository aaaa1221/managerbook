package com.cuong.junit.controller.commandcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cuong.controller.commandcontroller.BookCommandController;
import com.cuong.entity.Book;

import com.cuong.errorhandling.DuplicateCodeException;
import com.cuong.service.commandservice.IBookCommandService;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class CommandControllerTest {

	private MockMvc mvc;
	String json = " {\r\n" + "        \"uid\": 9,\r\n" + "        \"code\": \"b1\",\r\n"
			+ "        \"name\": \"vancuong\",\r\n" + "        \"description\": \"giọt lệ lung linh\",\r\n"
			+ "        \"category\": \"AAA\",\r\n" + "        \"author\": \"VanCuong\",\r\n"
			+ "        \"publisher\": \"mùa xuân nóng bỏng\",\r\n" + "        \"createUser\": \"cuong\",\r\n"
			+ "        \"createDate\": \"4/4/2019\",\r\n" + "        \"updateUser\": \"NVCUONG2\",\r\n"
			+ "        \"updateDate\": \"5/4/2019\"\r\n" + "    }";
	@Mock
	IBookCommandService bookCommandService;

	@InjectMocks
	BookCommandController bookCommandController;
	public List<Book> booksCasePass = new ArrayList<Book>();
	Book book1 = new Book(9, "b1", "vancuong", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng", "4/4/2019",
			"cuong", "5/4/2019", "NVCUONG2");
	Book book2 = new Book(100, "b123", "vancuong", "giọt lệ lung linh", "AAA", "VanCuong", "mùa xuân nóng bỏng",
			"4/4/2019", "cuong", "5/4/2019", "NVCUONG2");

	@Before
	public void setup() {
		booksCasePass.add(book1);
		booksCasePass.add(book2);
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(bookCommandController).build();
	}

	@Test
	public void createBookOk() throws Exception {
		when(bookCommandService.createBook(book1)).thenReturn(book1);
		mvc.perform(post("/books/create").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	public void createBookError() throws Exception {
		when(bookCommandService.createBook(book1)).thenThrow(new DuplicateCodeException("code is exist"));
		DuplicateCodeException dlcexception = assertThrows(DuplicateCodeException.class,
				() -> bookCommandService.createBook(book1));
		assertEquals(dlcexception.getMessage(), "code is exist");
		mvc.perform(post("/books/create").contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateBookByIdOk() throws Exception {

		when(bookCommandService.updateBook(9, book1)).thenReturn(book1);
		mvc.perform(put("/books/edit/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

	}

	@Test
	public void updateBookByCodeOk() throws Exception {

		when(bookCommandService.updateBookbyCode("b1", book1)).thenReturn(book1);
		mvc.perform(put("/books/editbycode/b1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteBookByIdOk() throws Exception {

		when(bookCommandService.deleteBook(9, true)).thenReturn(true);
		mvc.perform(delete("/books/delete/{bookId}", book1.getUid()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteBookByCodeOk() throws Exception {

		when(bookCommandService.deleteBookByCode("b1", true)).thenReturn(true);
		mvc.perform(delete("/books/deletebycode/{code}", book1.getCode()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
