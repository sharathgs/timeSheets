package com.timemanagement.util;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.timemanagement.dto.EmployeeDto;
import com.timemanagement.dto.LeaveDto;

@Configuration
public class TimeUtilRest {

	
	public LeaveDto getLeaves(String enrolledUrl)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<LeaveDto> response  = restTemplate.getForEntity(enrolledUrl, LeaveDto.class);
		return response.getBody();
	}
	
	public EmployeeDto getData(String enrolledUrl)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<EmployeeDto> response  = restTemplate.getForEntity(enrolledUrl, EmployeeDto.class);
		return response.getBody();
	}
	
}
