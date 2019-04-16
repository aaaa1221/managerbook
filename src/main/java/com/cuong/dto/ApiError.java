package com.cuong.dto;

import java.util.List;


import org.springframework.http.HttpStatus;

import com.cuong.errorhandling.BusinessErrorCodeE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//contains error msg
public class ApiError {
	public ApiError(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
	String message;
	List<String> details;
	BusinessErrorCodeE businessQueryErrorCode;
	HttpStatus httpStatus;
}
