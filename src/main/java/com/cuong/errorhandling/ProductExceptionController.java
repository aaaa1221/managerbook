package com.cuong.errorhandling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cuong.dto.ApiError;

@ControllerAdvice
public class ProductExceptionController extends ResponseEntityExceptionHandler {
	// return INTERNAL_SERVER_ERROR
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ApiError error = new ApiError("Server Error", details);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// return NOT_FOUND
	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(BookNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ApiError error = new ApiError("Record Not Found", details);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	// return BAD_REQUEST
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ApiError apiError = new ApiError("Validation Failed", details);
		apiError.setBusinessQueryErrorCode(BusinessErrorCodeE.InvalidParam);
		apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}
}
