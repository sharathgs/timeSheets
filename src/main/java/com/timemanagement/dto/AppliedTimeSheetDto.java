package com.timemanagement.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppliedTimeSheetDto {

	private LocalDate fromDate;
	private LocalDate toDate;
	private String employeeRemarks;
	private int employeeId;
}
