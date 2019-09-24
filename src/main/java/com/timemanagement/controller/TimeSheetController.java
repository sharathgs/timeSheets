package com.timemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timemanagement.dto.AppliedResponseDto;
import com.timemanagement.dto.AppliedTimeSheetDto;
import com.timemanagement.dto.ApproveTimeSheetDto;
import com.timemanagement.dto.ApprovedTimeSheetDto;
import com.timemanagement.service.TimeSheetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/hcl/timeSheets")
public class TimeSheetController {

	/**
	 * @author Sharath G S
	 * @apiNote leave request
	 * @apiNote leave approval
	 * @apiNote approval or rejection
	 */
	
	@Autowired
	TimeSheetService timeService;
	
	@PostMapping("/submission")
	public ResponseEntity<AppliedResponseDto> submissionTimeSheets(@RequestBody AppliedTimeSheetDto appliedLeave)
	{
		log.info("event for applied leave controller called");
		return new ResponseEntity<>(timeService.appliedLeave(appliedLeave),HttpStatus.OK);
	}
	
	@PostMapping("/approval")
	public ResponseEntity<ApprovedTimeSheetDto> approvalTimeSheets(@RequestBody ApproveTimeSheetDto approveLeave)
	{
		return new ResponseEntity<>(timeService.approvedLeave(approveLeave),HttpStatus.OK);
	}
}
