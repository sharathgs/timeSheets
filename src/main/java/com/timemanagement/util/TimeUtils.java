package com.timemanagement.util;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TimeUtils {

	/**
	 * @apiNote to get between dates number
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public long betweenDates(LocalDate fromDate, LocalDate toDate)
	{
		return Duration.between(fromDate.atStartOfDay(), toDate.atStartOfDay()).toDays();
	}
	
	
	/**
	 * @apiNote we get day like sunday, monday,tuesday
	 * @param date
	 * @return
	 */
	public String days(LocalDate date)
	{
		return date.getDayOfWeek().name();
	}
	
	
	/**
	 * @apiNote to check weekend or not
	 * @param date
	 * @return
	 */
	public LocalDate validateDate(LocalDate date)
	{
		if(days(date).equals(TimeEnum.SATURDAY.toString()) || days(date).equals(TimeEnum.SUNDAY.toString()))
		{
			log.debug("these days come in weekends");
			return null;
		}else
		{
			return date;
		}
		
	}
	
}
