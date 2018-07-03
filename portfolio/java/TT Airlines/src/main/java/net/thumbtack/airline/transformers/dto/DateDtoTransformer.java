package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightScheduleDto;
import net.thumbtack.airline.utils.dates.Dates;

import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

public class DateDtoTransformer {
	private static final String scheduleKey = "schedule";
	private static final String listDatesKey = "dates";

	public static DateDto create(Dates dates) {
		Map<String, Object> toInsertIntoDates = new TreeMap<>();
		if (dates.getPeriod() != null) {
			toInsertIntoDates.put(scheduleKey, new FlightScheduleDto(dates.getFromDate(), dates.getToDate(),
					dates.getPeriod()));
		}
		toInsertIntoDates.put(listDatesKey, dates.getDates());
		return new DateDto(toInsertIntoDates);
	}

	public static DateDto create(Date toDate, Date fromDate, String period) {
		Map<String, Object> toInsertIntoDates = new TreeMap<>();
		toInsertIntoDates.put(scheduleKey, new FlightScheduleDto(fromDate, toDate, period));
		return new DateDto(toInsertIntoDates);
	}

	public static DateDto create(Dates dates, String specialBuild) {
		Map<String, Object> toInsertIntoDates = new TreeMap<>();//TODO refact to map
		if(specialBuild.equals(scheduleKey)) {
			toInsertIntoDates.put(scheduleKey, new FlightScheduleDto(dates.getFromDate(), dates.getToDate(),
					dates.getPeriod()));
		} else if (specialBuild.equals(listDatesKey)) {
			toInsertIntoDates.put(listDatesKey, dates.getDates());
		}
		else {
			throw new NullPointerException("BAD_SPECIAL_KEY, TRY schedule, dates");
		}
		return new DateDto(toInsertIntoDates);
	}

	public static DateDto createDateDtoScheduleOnly(Dates dates) {
		Map<String, Object> toInsertIntoDates = new TreeMap<>();
			toInsertIntoDates.put(scheduleKey, new FlightScheduleDto(dates.getFromDate(), dates.getToDate(),
					dates.getPeriod()));
		return new DateDto(toInsertIntoDates);
	}

	public static DateDto createDateListOnly(Dates dates) {
		Map<String, Object> toInsertIntoDates = new TreeMap<>();
		toInsertIntoDates.put(listDatesKey, dates.getDates());
		return new DateDto(toInsertIntoDates);
	}
}
