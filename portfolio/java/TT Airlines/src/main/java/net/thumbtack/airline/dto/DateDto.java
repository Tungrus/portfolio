package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class DateDto {
	private String[] acceptableKeys = {"schedule", "dates"};
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Map<String, Object> flightSchedule;

	private boolean checkKey(String checkKey) {
		for(String key : acceptableKeys) {
			if(checkKey.equals(key)) {
				return true;
			}
		}
		return false;
	}


	public DateDto(Map<String, Object> flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	public DateDto() {
		flightSchedule = new TreeMap<>();
	}

	@JsonAnyGetter
	public Map<String, Object> getFlightSchedule() {
		return flightSchedule;
	}

	public void setFlightSchedule(Map<String, Object> flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	@JsonAnySetter
	public void setFlightScheduleToMap(String key, Object value) throws ParseException {

		if (key.equals(acceptableKeys[0])) {
			try {
				LinkedHashMap linkedHashMap = (LinkedHashMap) value;
				Date fromDate = Date.valueOf((String) linkedHashMap.get("fromDate"));
				Date toDate = Date.valueOf((String) linkedHashMap.get("toDate"));
				String period = (String) linkedHashMap.get("period");
				flightSchedule.put(key, new FlightScheduleDto(fromDate, toDate, period));
			} catch (ClassCastException e) {
				flightSchedule.put(key, new FlightScheduleDto(null, null, null));
			}
		} else if (key.equals(acceptableKeys[1])) {

			flightSchedule.put(key, value);
		}
	}
}
