/**
 * 
 */
package com.yash;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Mitali
 *
 */
@RestControllerAdvice
public class EmployeeExceptionHandler    {
	 @ExceptionHandler(EmployeeNotFoundException.class)
	    public ResponseEntity<ItemErrorResponse> handleException(EmployeeNotFoundException ine){
	        ItemErrorResponse errorResponse = new ItemErrorResponse();
	        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
	        errorResponse.setMessage(ine.getMessage());
	        errorResponse.setTimeStamp(System.currentTimeMillis());
	        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }

//	    @ExceptionHandler
//	    public ResponseEntity<ItemErrorResponse> handleException(Exception ex){
//	        ItemErrorResponse errorResponse = new ItemErrorResponse();
//	        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//	        errorResponse.setMessage(ex.getMessage());
//	        errorResponse.setTimeStamp(System.currentTimeMillis());
//	        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
//	    }

}
