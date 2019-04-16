package com.cuong.errorhandling;

import org.springframework.http.HttpStatus;


public class DuplicateCodeException extends BusinessException {
  private static final long serialVersionUID = -6309685693063362452L;

  public DuplicateCodeException(String message) {
    super(message, BusinessErrorCodeE.DuplicateCode, HttpStatus.INTERNAL_SERVER_ERROR);
  }
 
}
