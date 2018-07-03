package net.thumbtack.airline.context.dates;

import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightScheduleDto;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.transformers.dto.DateDtoTransformer;
import net.thumbtack.airline.transformers.model.ModelDateTransformer;
import net.thumbtack.airline.utils.dates.Dates;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DatesTestContext {
	private final String datesKey = "dates";
	private final String scheduleKey = "schedule";
	private final String allDays = "daily";
	private final String oddDays = "odd";
	private final String evenDays = "even";
	private final String daysOfWeek = "Sun,Mon,Tue,Wed,Thu,Fri,Sat";
	private final String daysOfMonth = "1,2,3,4,5,6,7";
	private final int fromYear = 110;
	private final int fromMonth = 6;
	private final int fromDay = 1;
	private final int toYear = 110;
	private final int toMonth = 10;
	private final int toDay = 1;
	private final Date toDate = new Date(toYear, toMonth, toDay);
	private final Date fromDate = new Date(fromYear, fromMonth, fromDay);

	private Dates datesFromAllPeriod;
	private Dates datesFromOddPeriod;
	private Dates datesFromEvenPeriod;
	private Dates datesFromDaysOfWeek;
	private Dates datesFromDaysOfMonth;
	private Dates datesFromList;
	private List<Date> dateList = new ArrayList<>();

	{
		dateList.add(toDate);
		dateList.add(fromDate);
	}

	private Dates createDates(String key, String period) throws BaseApplicationException {
		Map<String, Object> dates = new TreeMap<>();
		dates.put(key, new FlightScheduleDto(fromDate, toDate, period));
		return ModelDateTransformer.create(new DateDto(dates));
	}

	public DatesTestContext() {

	}

	public String getAllDays() {
		return allDays;
	}

	public String getOddDays() {
		return oddDays;
	}

	public String getEvenDays() {
		return evenDays;
	}

	public String getByDaysOfWeek() {
		return daysOfWeek;
	}

	public String getByDaysOfMonth() {
		return daysOfMonth;
	}

	public int getFromYear() {
		return fromYear;
	}

	public int getFromMonth() {
		return fromMonth;
	}

	public int getFromDay() {
		return fromDay;
	}

	public int getToYear() {
		return toYear;
	}

	public int getToMonth() {
		return toMonth;
	}

	public int getToDay() {
		return toDay;
	}

	public Date getToDate() {
		return toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Dates getDatesFromAllPeriod() throws BaseApplicationException {
		return createDates(scheduleKey, allDays);
	}

	public DateDto getDatesAllPeriod () {
		return DateDtoTransformer.create(toDate, fromDate, allDays);
	}

	public Dates getDatesFromOddPeriod() throws BaseApplicationException {
		return createDates(scheduleKey, oddDays);
	}

	public Dates getDatesFromEvenPeriod() throws BaseApplicationException {
		return createDates(scheduleKey, evenDays);
	}

	public Dates getDatesFromDaysOfWeek() throws BaseApplicationException {
		return createDates(scheduleKey, daysOfWeek);
	}

	public Dates getDatesFromDaysOfMonth() throws BaseApplicationException {
		return createDates(scheduleKey, daysOfMonth);
	}

	public Dates getDatesFromList() {
		return new Dates(dateList, null, null, null);
	}
}
