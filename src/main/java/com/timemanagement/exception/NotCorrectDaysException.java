package com.timemanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotCorrectDaysException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotCorrectDaysException(String message2) {
		super(message2);

	}

	private String message;

}
