package com.timemanagement.util;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeManagementUtil {
	
	public TimeManagementUtil()
	{
		super();
	}

	public static final String APPLIED_TIMESHEETS="applied timesheets by employee";
	public static final String APPROVED_TIMESHEETS="approved leave from manager";
	public static final String DATES_MISMATCH="dates are mismatched";
	public static final String EMPLOYEE_NOT_DATA="Employee details not found";
	public static final String WEEKEND_DAY="weekend day";
	public static final String NOT_APPROVED="Time Sheet Not Approved";
	public static final String TIMESHEET_APPLIED = "Employee already submitted timesheets";
	
	
}
