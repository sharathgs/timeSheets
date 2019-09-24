package com.timemanagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timemanagement.dto.EmployeeDto;
import com.timemanagement.dto.LeaveDto;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestResponse {
	
	@Autowired
	TimeUtilRest timeRest;
	
	@Autowired
	ObjectMapper mapper;
	
	public EmployeeDto validateEmployee(String employeeUrl)
	{
		EmployeeDto employeeDto =  timeRest.getData(employeeUrl);
		log.debug("employee details for current employee");
		return employeeDto;
	}
	
	public LeaveDto getLeaves(String leaveUrl)
	{
		LeaveDto leaves = timeRest.getLeaves(leaveUrl);
		log.debug("leave details for current year");
		return leaves;
	}

}
