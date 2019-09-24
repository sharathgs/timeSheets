package com.timemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	
	private int employeeId;
	private String employeeName;
	private String employeeDesignation;
	private String mobileNo;
	private String password;
	private int statusCode;
	private String message;
}
