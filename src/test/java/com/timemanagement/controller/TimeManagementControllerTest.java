package com.timemanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.timemanagement.dto.AppliedResponseDto;
import com.timemanagement.dto.AppliedTimeSheetDto;
import com.timemanagement.dto.ApproveTimeSheetDto;
import com.timemanagement.dto.ApprovedTimeSheetDto;
import com.timemanagement.service.TimeSheetService;
import com.timemanagement.util.TimeManagementUtil;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TimeManagementControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	TimeSheetController timeController;
	
	@Mock
	TimeSheetService timeService;
	
	ApproveTimeSheetDto approveLeaveDto;
	ApprovedTimeSheetDto approvedLeaveDto;
	AppliedTimeSheetDto applyTimeSheet;
	AppliedResponseDto appliedDto;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(timeController).build();
        
        approveLeaveDto= new ApproveTimeSheetDto();
        approveLeaveDto.setEmployeeId(1);
        approveLeaveDto.setId(1);
        approveLeaveDto.setManagerId(1);
        approveLeaveDto.setManagerRemarks("I am approving");
        
        approvedLeaveDto = new ApprovedTimeSheetDto();
        approvedLeaveDto.setMessage(TimeManagementUtil.APPROVED_TIMESHEETS);
        approvedLeaveDto.setStatusCode(HttpStatus.OK.value());
        
        
        applyTimeSheet = new AppliedTimeSheetDto();
        applyTimeSheet.setEmployeeId(1);
        applyTimeSheet.setEmployeeRemarks("Please approve time sheets");
        applyTimeSheet.setFromDate(LocalDate.of(2019, 12, 21));
        applyTimeSheet.setToDate(LocalDate.of(2019, 12, 26));
        
        appliedDto = new AppliedResponseDto();
        appliedDto.setMessage(TimeManagementUtil.APPLIED_TIMESHEETS);
        appliedDto.setStatusCode(HttpStatus.OK.value());
    }
	
	
	
	@Test
	public void approveTimeSheet()
	{
		ResponseEntity<ApprovedTimeSheetDto> expResult = new ResponseEntity<>(approvedLeaveDto, HttpStatus.OK);
		when(timeService.approvedLeave(approveLeaveDto)).thenReturn(approvedLeaveDto);
		ResponseEntity<ApprovedTimeSheetDto> actResult = timeController.approvalTimeSheets(approveLeaveDto);
		assertEquals(expResult.getStatusCode(), actResult.getStatusCode());
	}
	
	@Test
	public void applyTimeSheet()
	{
		ResponseEntity<AppliedResponseDto> expResult = new ResponseEntity<>(appliedDto, HttpStatus.OK);
		when(timeService.appliedLeave(applyTimeSheet)).thenReturn(appliedDto);
		ResponseEntity<AppliedResponseDto> actResult = timeController.submissionTimeSheets(applyTimeSheet);
		assertEquals(expResult.getStatusCode(), actResult.getStatusCode());
	}
}
