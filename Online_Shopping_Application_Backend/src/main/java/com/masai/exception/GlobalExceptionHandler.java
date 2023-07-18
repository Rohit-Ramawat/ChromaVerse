package com.masai.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetail> myExceptionHandler(Exception ex,WebRequest req){
		
		MyErrorDetail error = new MyErrorDetail(ex.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetail> noHandlerFoundHandler(NoHandlerFoundException ex,WebRequest req){
		
		MyErrorDetail error = new MyErrorDetail(ex.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetail> dataValidationHandler(MethodArgumentNotValidException ex){
		
		MyErrorDetail error = new MyErrorDetail();
		
		error.setErrorDateTime(LocalDateTime.now());
		
		error.setMessage("Validation Exception");
		
		error.setDescription(ex.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetail>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
