package com.cuong.util;

import java.util.List;

import com.cuong.entity.Book;
import com.cuong.errorhandling.DuplicateCodeException;
import com.cuong.errorhandling.ValidationException;

public class ValidParam {

	public static boolean validTypeOfDate(String date) {
		try {
			return true;
		} catch (Exception e) {
			throw new ValidationException("CreateDate or UpdateDate should be date");
		}
	}

	public static void checkSizeCode(String param, int length) {
		if (param.length() > length) {
			throw new ValidationException("Size of " + param + " should be less than " + length);
		} else if (param.isEmpty()) {
			throw new ValidationException(param + " should not be null");
		}
	}

	public static void validBookId(int bookId) {
		if (bookId < 1) {
			throw new ValidationException("Book id should be greater than 1");
		}
	}

	public static void checkUniqueCode(List<String> codes, String code) {
		if (codes.contains(code)) {
			throw new DuplicateCodeException("Code should be unique");
		}
	}

	public static void checkUniqueBookId(List<Integer> bookIds, int bookId) {
		if (bookIds.contains(bookId)) {
			throw new DuplicateCodeException("BookId should be unique");
		}
	}

	public static void checkSizeParamOfBook(Book book) {
		if (book.getCode().length() > 20) {
			throw new ValidationException("Size of code should be less than 20");
		} else if (book.getCode().isEmpty()) {
			throw new ValidationException("Code should not be null");
		}

		if (book.getName().length() > 255) {
			throw new ValidationException("Size of name should be less than 255");
		} else if (book.getName().isEmpty()) {
			throw new ValidationException("Name should not be null");
		}

		if (book.getCategory().length() > 255) {
			throw new ValidationException("Size of category should be less than 255");
		} else if (book.getCategory().isEmpty()) {
			throw new ValidationException("Category should not be null");
		}

		if (book.getAuthor().length() > 255) {
			throw new ValidationException("Size of author should be less than 255");
		} else if (book.getAuthor().isEmpty()) {
			throw new ValidationException("Author should not be null");
		}

		if (book.getCreateUser().length() > 255) {
			throw new ValidationException("Size of create user should be less than 255");
		} else if (book.getCreateUser().isEmpty()) {
			throw new ValidationException("Create user should not be null");
		}

		if (book.getCreateDate().length() > 255) {
			throw new ValidationException("Size of create date should be less than 255");
		} else if (book.getCreateDate().isEmpty()) {
			throw new ValidationException("Create date should not be null");
		}

		if (book.getUpdateUser().length() > 255) {
			throw new ValidationException("Size of update user should be less than 255");
		} else if (book.getUpdateUser().isEmpty()) {
			throw new ValidationException("Update user should not be null");
		}

		if (book.getUpdateDate().length() > 255) {
			throw new ValidationException("Size of update date should be less than 255");
		} else if (book.getUpdateDate().isEmpty()) {
			throw new ValidationException("Update date should not be null");
		}
	}

}
