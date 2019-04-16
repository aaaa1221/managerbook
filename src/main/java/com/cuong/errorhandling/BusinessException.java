package com.cuong.errorhandling;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {


  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected BusinessErrorCodeE businessErrorCode;
  protected HttpStatus statusCode;
  
  
  public HttpStatus getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(HttpStatus statusCode) {
    this.statusCode = statusCode;
  }

  public BusinessErrorCodeE getBusinessErrorCode() {
    return businessErrorCode;
  }

  public void setBusinessErrorCode(BusinessErrorCodeE businessErrorCode) {
    this.businessErrorCode = businessErrorCode;
  }

  public BusinessException(String message, BusinessErrorCodeE businessErrorCode, HttpStatus statusCode) {
    super(message);
    this.businessErrorCode = businessErrorCode;
    this.statusCode = statusCode;
  }
  
  public BusinessException(String message) {
    super(message);
    this.businessErrorCode = BusinessErrorCodeE.InternalServerError;
    this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
  }
  
  
}
