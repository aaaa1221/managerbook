package com.cuong.errorhandling;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BusinessException {

  

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public BookNotFoundException(String message) {
    super(message, BusinessErrorCodeE.BookNotFound, HttpStatus.NOT_FOUND);
  }

}
