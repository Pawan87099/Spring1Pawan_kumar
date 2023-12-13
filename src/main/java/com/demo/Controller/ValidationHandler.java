package com.demo.Controller;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request ){
	Map<String, String> errors=new HashMap<>();
	//getBindingResult to bind all exceptions
	//getAllErrors to get all error in list
	ex.getBindingResult().getAllErrors().forEach((error)->{
		//getField returns effected field that has error, it returns field error object that is why we need to typecast it
		String fieldName=((FieldError)error).getField();
		String message = error.getDefaultMessage();
		errors.put(fieldName, message);
	});
	return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
