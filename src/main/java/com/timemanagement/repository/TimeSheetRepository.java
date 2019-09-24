package com.timemanagement.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timemanagement.model.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {

	public List<TimeSheet> findAllByEmployeeId(int employeeId);
	
}
