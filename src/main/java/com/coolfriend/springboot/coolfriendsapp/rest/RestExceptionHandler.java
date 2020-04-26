package com.coolfriend.springboot.coolfriendsapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class RestExceptionHandler {
	
	// Add an exception handler for UserNotFoundException
	
		@ExceptionHandler
		public ResponseEntity<RestErrorResponse> handleException(RestNotFoundException exc) {
			
			//create UserErrorResponse
			RestErrorResponse error = new RestErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		//Catch all
		
		@ExceptionHandler
		public ResponseEntity<RestErrorResponse> handleException(Exception exc) {
			
			//create UserErrorResponse
			RestErrorResponse error = new RestErrorResponse(HttpStatus.BAD_REQUEST.value(), 
					exc.getMessage(), 
					System.currentTimeMillis());
			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

}
