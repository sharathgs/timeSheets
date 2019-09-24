package com.timemanagement.service;

import org.springframework.stereotype.Service;


import com.timemanagement.dto.AppliedResponseDto;
import com.timemanagement.dto.AppliedTimeSheetDto;
import com.timemanagement.dto.ApproveTimeSheetDto;
import com.timemanagement.dto.ApprovedTimeSheetDto;

@Service
public interface TimeSheetService {

	public AppliedResponseDto appliedLeave(AppliedTimeSheetDto appliedLeave);
	
	public ApprovedTimeSheetDto approvedLeave(ApproveTimeSheetDto approveLeave);
	
}
