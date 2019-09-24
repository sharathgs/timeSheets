package com.timemanagement.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.timemanagement.dto.AppliedResponseDto;
import com.timemanagement.dto.AppliedTimeSheetDto;
import com.timemanagement.dto.ApproveTimeSheetDto;
import com.timemanagement.dto.ApprovedTimeSheetDto;
import com.timemanagement.dto.CompanyLeavesDto;
import com.timemanagement.dto.EmployeeDto;
import com.timemanagement.dto.LeaveDto;
import com.timemanagement.exception.EmployeeNotFound;
import com.timemanagement.exception.NotCorrectDaysException;
import com.timemanagement.exception.TimeSheetNotApprovedException;
import com.timemanagement.model.TimeSheet;
import com.timemanagement.repository.TimeSheetRepository;
import com.timemanagement.util.RestResponse;
import com.timemanagement.util.StatusEnum;
import com.timemanagement.util.TimeManagementUtil;
import com.timemanagement.util.TimeUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeSheetServiceImpl implements TimeSheetService {

	/**
	 * @author Sharath G S
	 * @apiNote applied leave
	 */
	
	@Autowired
	TimeSheetRepository timeRepository;
	
	@Autowired
	TimeUtils timeUtils;
	
	@Value("${employee.url}")
	private String enrolledEmployee;
	
	@Value("${leaves.url}")
	private String enrolledLeaves;
	
	@Autowired
	RestResponse restResponse;
	
	public AppliedResponseDto appliedLeave(AppliedTimeSheetDto appliedLeave) {
		
		log.info("applied leave date service event called");
		AppliedResponseDto alResponse = new AppliedResponseDto();
		LocalDate fromDate = appliedLeave.getFromDate();
		LocalDate toDate = appliedLeave.getToDate().plusDays(1);
		int employeeId = appliedLeave.getEmployeeId();		
		
		EmployeeDto employeeData = restResponse.validateEmployee(enrolledEmployee+employeeId);
		LeaveDto leaves = restResponse.getLeaves(enrolledLeaves);
		
		List<CompanyLeavesDto> chLeaves = leaves.getLeaveDate();
		List<LocalDate> getDates = chLeaves.stream().map(CompanyLeavesDto::getLeaveDate).collect(Collectors.toList());
		
		List<TimeSheet> timeSheet = new ArrayList<>();		
		List<LocalDate> employeeTimeSheet = timeRepository.findAllByEmployeeId(employeeId).stream().map(TimeSheet::getApplieddate).collect(Collectors.toList());
		
		
		if(timeUtils.betweenDates(fromDate, toDate) <= 0)
		{
			throw new NotCorrectDaysException(TimeManagementUtil.DATES_MISMATCH);
		}else if(employeeData.getEmployeeDesignation() == null)
		{
			throw new EmployeeNotFound(TimeManagementUtil.EMPLOYEE_NOT_DATA);
		}else{
		
			Stream.iterate(fromDate, day->day.plusDays(1)).
			limit(ChronoUnit.DAYS.between(fromDate, toDate)).
			filter(days -> !employeeTimeSheet.contains(days))
			.filter(days -> !getDates.contains(days))
			.forEach(day->{
				
				if(timeUtils.validateDate(day) != null)
				{
					log.info(day.toString());
					TimeSheet ts = new TimeSheet();
					BeanUtils.copyProperties(appliedLeave, ts);
					ts.setRequestedDate(LocalDate.now());
					ts.setApplieddate(day);
					ts.setPresenceStatus(StatusEnum.PENDING.name());
					timeSheet.add(ts);
				}
				
			});
			alResponse.setStatusCode(HttpStatus.OK.value());
			alResponse.setMessage(TimeManagementUtil.APPLIED_TIMESHEETS);
			timeRepository.saveAll(timeSheet);
			return alResponse;
		}
						
	}

	
	/**
	 * @author Sharath G S
	 * @apiNote approved leave
	 */
	public ApprovedTimeSheetDto approvedLeave(ApproveTimeSheetDto approveLeave) {		
		
		log.info("event for approve leave");
	Optional<TimeSheet> timeSheetDetails = timeRepository.findById(approveLeave.getId());
	ApprovedTimeSheetDto approvedLeave = new ApprovedTimeSheetDto();
	
		timeSheetDetails.ifPresent(leave -> {
			
			TimeSheet ts = new TimeSheet();
			BeanUtils.copyProperties(timeSheetDetails.get(), ts);
			BeanUtils.copyProperties(approveLeave, ts);
			ts.setApprovedDate(LocalDate.now());
			approvedLeave.setMessage(TimeManagementUtil.APPROVED_TIMESHEETS);
			approvedLeave.setStatusCode(HttpStatus.OK.value());
			ts.setManagerRemarks(approveLeave.getManagerRemarks());
			ts.setPresenceStatus(StatusEnum.APPROVE.name());
			timeRepository.save(ts);
		});
		timeSheetDetails.orElseThrow(() -> new TimeSheetNotApprovedException(TimeManagementUtil.NOT_APPROVED, HttpStatus.NOT_FOUND.value()));
		return approvedLeave;
	}

	
	
}
