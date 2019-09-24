package com.timemanagement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {

	private List<CompanyLeavesDto> leaveDate;
	private int statusCode;
	private String message;
}
