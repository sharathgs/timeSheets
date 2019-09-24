package com.timemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeNotFound  extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
}
