package com.timemanagement.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.timemanagement.dto.AppliedResponseDto;
import com.timemanagement.dto.AppliedTimeSheetDto;
import com.timemanagement.dto.ApproveTimeSheetDto;
import com.timemanagement.dto.ApprovedTimeSheetDto;
import com.timemanagement.dto.EmployeeDto;
import com.timemanagement.exception.TimeSheetNotApprovedException;
import com.timemanagement.model.TimeSheet;
import com.timemanagement.repository.TimeSheetRepository;
import com.timemanagement.util.RestResponse;
import com.timemanagement.util.TimeUtils;

@RunWith(MockitoJUnitRunner.class)
public class TimeManagesmentServiceTest {

	@InjectMocks
	TimeSheetServiceImpl timeService;
	
	@Mock
	TimeSheetRepository timeRepository;
	
	@Autowired
	RestResponse restResponse;
	
	@Autowired
	TimeUtils timeUtils;
	
	@Value("${service.url.employee}")
	private String enrolledEmployee;
	
	@Value("${service.url.leaves}")
	private String enrolledLeaves;
	
	TimeSheet timeSheet;	
	ApproveTimeSheetDto approveLeave;
	TimeSheet timeSheet1;
	TimeSheet timeSheet2;
	TimeSheet timeSheet3;
	EmployeeDto employeeDto;
	AppliedTimeSheetDto appliedLeaveDto;
	AppliedResponseDto appliedResponseDto;
	
	@Before
	public void setup()
	{
		timeSheet = new TimeSheet();
		timeSheet.setApplieddate(LocalDate.of(2019, 12, 20));
		timeSheet.setEmployeeId(1);
		timeSheet.setEmployeeRemarks("not feeling well");
		timeSheet.setId(1);
		timeSheet.setManagerRemarks("approved and get well soon");
		timeSheet.setPresenceStatus("APPROVE");
		
		timeSheet2 = new TimeSheet();
		timeSheet2.setApplieddate(LocalDate.of(2019, 12, 21));
		timeSheet2.setEmployeeId(1);
		timeSheet2.setEmployeeRemarks("not feeling well");
		timeSheet2.setId(1);
		timeSheet2.setManagerRemarks("approved and get well soon");
		timeSheet2.setPresenceStatus("APPROVE");
		
		timeSheet3 = new TimeSheet();
		timeSheet3.setApplieddate(LocalDate.of(2019, 12, 21));
		timeSheet3.setEmployeeId(1);
		timeSheet3.setEmployeeRemarks("not feeling well");
		timeSheet3.setId(1);
		timeSheet3.setManagerRemarks("approved and get well soon");
		timeSheet3.setPresenceStatus("APPROVE");
		
		approveLeave = new ApproveTimeSheetDto();
		approveLeave.setEmployeeId(1);
		approveLeave.setId(1);
		approveLeave.setManagerRemarks("approved and get well soon");
		
		employeeDto = new EmployeeDto();
		employeeDto.setEmployeeDesignation("SE");
		employeeDto.setEmployeeId(1);
		employeeDto.setEmployeeName("Sharath");
		employeeDto.setMessage("employeeDetails");
		employeeDto.setMobileNo("9738129042");
		employeeDto.setPassword("test@123");
		employeeDto.setStatusCode(200);
		
		appliedLeaveDto = new AppliedTimeSheetDto();
		appliedLeaveDto.setEmployeeId(1);
		appliedLeaveDto.setEmployeeRemarks("approved");
		appliedLeaveDto.setFromDate(LocalDate.of(2019, 12, 20));
		appliedLeaveDto.setToDate(LocalDate.of(2019, 12, 22));
		
		appliedResponseDto = new AppliedResponseDto();
		appliedResponseDto.setMessage("applied for leave by employee");
		appliedResponseDto.setStatusCode(200);
		
	}
	
	@Test
	public void ApprovedLeaveTest()
	{
		Mockito.when(timeRepository.findById(1)).thenReturn(Optional.of(timeSheet));
		ApprovedTimeSheetDto timeSheetResult = timeService.approvedLeave(approveLeave) ;
		Assert.assertEquals(timeSheetResult.getStatusCode(),HttpStatus.OK);
	}
	
	
	@Test(expected = TimeSheetNotApprovedException.class)
	public void ApprovedLeaveTestNotApproved()
	{
		Mockito.when(timeRepository.findById(2)).thenReturn(Optional.of(timeSheet));
		ApprovedTimeSheetDto timeSheetResult = timeService.approvedLeave(approveLeave) ;
		Assert.assertEquals(timeSheetResult.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	
	@Test
	public void ApplyLeaveTest()
	{
		
		List<TimeSheet> timeSheetAll =new ArrayList<TimeSheet>();
		timeSheetAll.add(timeSheet1);
		timeSheetAll.add(timeSheet2);
		timeSheetAll.add(timeSheet3);
		
		/*
		 * EmployeeDto employeeData =
		 * restResponse.validateEmployee("http://localhost:8088/hcl/hcl/employees/"+
		 * employeeDto.getEmployeeId()); LeaveDto leaves =
		 * restResponse.getLeaves(enrolledLeaves);
		 * 
		 * List<CompanyLeavesDto> chLeaves = leaves.getLeaveDate(); List<LocalDate>
		 * getDates =
		 * chLeaves.stream().map(CompanyLeavesDto::getLeaveDate).collect(Collectors.
		 * toList());
		 */
		
		Mockito.when(timeRepository.saveAll(timeSheetAll));
		AppliedResponseDto appliedLeave = timeService.appliedLeave(appliedLeaveDto);
		Assert.assertEquals(appliedLeave.getStatusCode(),HttpStatus.OK);
	}
	
	
}
