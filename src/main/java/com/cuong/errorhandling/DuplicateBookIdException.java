package com.cuong.errorhandling;

import org.springframework.http.HttpStatus;

public class DuplicateBookIdException extends BusinessException {
  private static final long serialVersionUID = 4826740890008323538L;

  public DuplicateBookIdException(String message) {
    super(message, BusinessErrorCodeE.DuplicateBookId, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
}
