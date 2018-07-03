package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightScheduleDto;
import net.thumbtack.airline.errors.types.runtime.ScheduleError;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.utils.dates.Dates;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;


public class ModelDateTransformer {

	private final static Map<String, DayOfWeek> DAY_OF_WEEK_MAP = new TreeMap();
	private final static Pattern patternDayOfMount = Pattern.compile("^\\d{1,2}?(,\\d{1,2})*");
	private static final String monday = "Mon";
	private static final String tuesday = "Tue";
	private static final String wednesday = "Wed";
	private static final String thursday = "Thu";
	private static final String friday = "Fri";
	private static final String saturday = "Sat";
	private static final String sunday = "Sun";

	private static final String datesKey = "dates";
	private static final String scheduleKey = "schedule";

	static {
		DAY_OF_WEEK_MAP.put(monday, DayOfWeek.MONDAY);
		DAY_OF_WEEK_MAP.put(tuesday, DayOfWeek.TUESDAY);
		DAY_OF_WEEK_MAP.put(wednesday, DayOfWeek.WEDNESDAY);
		DAY_OF_WEEK_MAP.put(thursday, DayOfWeek.THURSDAY);
		DAY_OF_WEEK_MAP.put(friday, DayOfWeek.FRIDAY);
		DAY_OF_WEEK_MAP.put(saturday, DayOfWeek.SATURDAY);
		DAY_OF_WEEK_MAP.put(sunday, DayOfWeek.SUNDAY);
	}

	interface Fetchable {
		boolean fetch(LocalDate localDate);
	}


	public static Dates create(DateDto dateDto) throws BaseApplicationException {
		if (dateDto.getFlightSchedule().containsKey(scheduleKey)) {
			FlightScheduleDto flightScheduleDto = (FlightScheduleDto) dateDto.getFlightSchedule().get(scheduleKey);
			List<Date> dates = create(flightScheduleDto.getFromDate(), flightScheduleDto.getToDate(),
					flightScheduleDto.getPeriod());
			return new Dates(dates, flightScheduleDto.getToDate(), flightScheduleDto.getFromDate(),
					flightScheduleDto.getPeriod());
		} else {
			List<Date> dateList = (List<Date>) dateDto.getFlightSchedule().get(datesKey);
			return new Dates(dateList, null, null, null);
		}
	}

	private static List<Date> create(Date fromDate, Date toDate, String period) throws BaseApplicationException {
		List<Date> dates = new ArrayList<>();
		LocalDate fromLocalDate = fromDate.toLocalDate();
		LocalDate toLocalDate = toDate.toLocalDate();
		List<LocalDate> localDates = new ArrayList<>();
		if (period.equals("daily")) { //TODO refact To Map
			localDates = createAllDates(fromLocalDate, toLocalDate);
		} else if (period.equals("odd")) {
			localDates = createDatesByCondition(fromLocalDate, toLocalDate, (LocalDate localDate) -> {
				return localDate.getDayOfMonth() % 2 != 0;
			});
		} else if (period.equals("even")) {
			localDates = createDatesByCondition(fromLocalDate, toLocalDate, (LocalDate localDate) -> {
				return localDate.getDayOfMonth() % 2 == 0;
			});
		} else if (period.startsWith(monday) || period.startsWith(tuesday) || period.startsWith(thursday)
				|| period.startsWith(wednesday) || period.startsWith(friday) || period.startsWith(saturday) ||
				period.startsWith(sunday)) {
			localDates = createDatesByDaysOfWeek(fromLocalDate, toLocalDate, period.split(","));
		} else if (patternDayOfMount.matcher(period).matches()) {
			List<Integer> daysList = new ArrayList<>();
			String[] daysFromPeriod = period.split(",");
			for (String date : daysFromPeriod) {
				daysList.add(Integer.valueOf(date));
			}
			localDates = createDatesByDaysInMount(fromLocalDate, toLocalDate, daysList);
		} else {
			throw new BaseApplicationException(new ScheduleError());
		}
		return create(localDates);
	}

	private static List<LocalDate> createAllDates(LocalDate fromLocalDate, LocalDate toLocalDate) {
		List<LocalDate> localDates = new ArrayList<>();
		while (toLocalDate.isAfter(fromLocalDate) || toLocalDate.equals(fromLocalDate)) {
			localDates.add(fromLocalDate);
			fromLocalDate = fromLocalDate.plusDays(1);
		}
		return localDates;
	}

	private static List<LocalDate> createDatesByCondition(LocalDate fromLocalDate, LocalDate toLocalDate,
														  Fetchable condition) {
		List<LocalDate> localDates = new ArrayList<>();
		while (toLocalDate.isAfter(fromLocalDate) || toLocalDate.equals(fromLocalDate)) {
			if (condition.fetch(fromLocalDate)) {
				localDates.add(fromLocalDate);
			}
			fromLocalDate = fromLocalDate.plusDays(1);

		}
		return localDates;
	}

	private static List<LocalDate> createDatesByDaysOfWeek(LocalDate fromLocalDate, LocalDate toLocalDate,
														   String[] daysOfWeek) {
		List<LocalDate> localDates = new ArrayList<>();
		for (int i = 0; i != daysOfWeek.length; i++) {
			DayOfWeek day = DAY_OF_WEEK_MAP.get(daysOfWeek[i]);
			int nearDay = day.getValue() - fromLocalDate.getDayOfWeek().getValue();
			LocalDate buffFromLocalDate = fromLocalDate.plusDays((7 + nearDay) % 7);
			generateDayOfWeek(buffFromLocalDate, toLocalDate, localDates);
		}
		return localDates;
	}

	private static void generateDayOfWeek(LocalDate fromLocalDate, LocalDate toLocalDate, List<LocalDate> result) {
		LocalDate bufferedFromDate = fromLocalDate;
		while (toLocalDate.isAfter(bufferedFromDate) || toLocalDate.equals(bufferedFromDate)) {
			result.add(bufferedFromDate);
			bufferedFromDate = bufferedFromDate.plusDays(7);
		}
	}

	private static List<LocalDate> createDatesByDaysInMount(LocalDate fromLocalDate, LocalDate toLocalDate,
															List<Integer> daysOfWeek) {
		List<LocalDate> localDates = new ArrayList<>();
		for (Integer day : daysOfWeek) {
			LocalDate bufferedFromLocalDate = fromLocalDate;
			if (day < bufferedFromLocalDate.getDayOfMonth()) {
				bufferedFromLocalDate = bufferedFromLocalDate.plusMonths(1);
			}
			bufferedFromLocalDate = bufferedFromLocalDate.plusDays(day - bufferedFromLocalDate.getDayOfMonth());
			while (toLocalDate.isAfter(bufferedFromLocalDate) || toLocalDate.equals(bufferedFromLocalDate)) {
				if (bufferedFromLocalDate.lengthOfMonth() > day) {
					localDates.add(bufferedFromLocalDate);
					//bufferedFromLocalDate.plusDays(day - bufferedFromLocalDate.getDayOfMonth());
				}
				bufferedFromLocalDate = bufferedFromLocalDate.plusMonths(1);
			}
		}
		return localDates;
	}

	private static List<Date> create(List<LocalDate> localDates) {
		List<Date> dates = new ArrayList<>();
		for (LocalDate localDate : localDates) {
			dates.add(Date.valueOf(localDate));
		}
		return dates;
	}
}
