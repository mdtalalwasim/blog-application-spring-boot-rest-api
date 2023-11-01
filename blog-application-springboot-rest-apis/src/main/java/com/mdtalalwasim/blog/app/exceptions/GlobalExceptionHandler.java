package com.mdtalalwasim.blog.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mdtalalwasim.blog.app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse  = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			
			response.put(fieldName, defaultMessage);
			
		});;
		
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiExceptionHandler.class)
	public ResponseEntity<ApiResponse> apiExceptionHandler(ApiExceptionHandler ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse  = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}

}
