package com.cuong.errorhandling;

import org.springframework.http.HttpStatus;

public class ValidationException extends BusinessException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ValidationException(String message) {
    super(message, BusinessErrorCodeE.ValidationInvalidParameter, HttpStatus.BAD_REQUEST);
  }

}
