package com.timemanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveTimeSheetDto {

	private int id;
	private int employeeId;
	private String managerRemarks;
	private int managerId;
	
}
