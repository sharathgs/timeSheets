package com.timemanagement.exception;


import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception,WebRequest request) {
		  ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(),
		  exception.getMessage());
		  
		  return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		  
		  }
	
	
	  @ExceptionHandler(NotCorrectDaysException.class) public
	  ResponseEntity<NotCorrectDaysException>
	  globalExceptionHandler(NotCorrectDaysException exception,WebRequest request)
	  { NotCorrectDaysException errorResponse = new NotCorrectDaysException(
	  exception.getMessage());
	  
	  return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	  
	  }
	  
	  
	  @ExceptionHandler(EmployeeNotFound.class) public
	  ResponseEntity<EmployeeNotFound> globalExceptionHandler(EmployeeNotFound
	  exception,WebRequest request) { EmployeeNotFound errorResponse = new
	  EmployeeNotFound( exception.getMessage());
	  
	  return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	  
	  }
	  
	  @ExceptionHandler(LeaveAppliedException.class) 
	  public ResponseEntity<LeaveAppliedException> globalExceptionHandler(LeaveAppliedException
	  exception,WebRequest request) { LeaveAppliedException errorResponse = new
			  LeaveAppliedException( exception.getMessage());
	  
	  return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	  
	  }
	 
	
}