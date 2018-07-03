package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Date;

public class OrderParamsDto {
	private int flightId;
	private Date date;

	public OrderParamsDto() {
	}

	public OrderParamsDto(int flightId, Date date) {
		this.flightId = flightId;
		this.date = date;
	}

	@JsonGetter("flightId")
	public int getFlightId() {
		return flightId;
	}
	@JsonSetter("flightId")
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	@JsonGetter("date")
	public Date getDate() {
		return date;
	}
	@JsonSetter("date")
	public void setDate(Date date) {
		this.date = date;
	}
}
