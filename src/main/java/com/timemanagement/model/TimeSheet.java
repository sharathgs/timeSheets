package com.timemanagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int employeeId;
	private LocalDate applieddate;
	private String employeeRemarks;
	private String presenceStatus;
	private String managerRemarks;
	private LocalDate requestedDate;
	private LocalDate approvedDate;
	
}
