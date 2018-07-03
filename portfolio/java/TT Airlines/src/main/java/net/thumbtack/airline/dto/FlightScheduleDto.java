package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Date;

public class FlightScheduleDto {
	private Date fromDate;
	private Date toDate;
	private String period;

	public FlightScheduleDto(Date fromDate, Date toDate, String period) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.period = period;
	}

	public FlightScheduleDto() {
	}

	@JsonGetter("fromDate")
	public Date getFromDate() {
		return fromDate;
	}

	@JsonSetter("fromDate")
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@JsonGetter("toDate")
	public Date getToDate() {
		return toDate;
	}

	@JsonSetter("toDate")
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@JsonGetter("period")
	public String getPeriod() {
		return period;
	}

	@JsonSetter("period")
	public void setPeriod(String period) {
		this.period = period;
	}
}
