package com.timemanagement.exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {

	public ErrorResponse(LocalDate now, String message2) {
	}
	private LocalDate timestamp;
	private String message;
	

}
