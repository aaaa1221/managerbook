package com.cuong.errorhandling;

public class BookException {
  public BookException() {}

  public static class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
  }
}
