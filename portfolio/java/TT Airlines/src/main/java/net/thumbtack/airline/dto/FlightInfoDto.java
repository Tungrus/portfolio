package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Time;

public class FlightInfoDto {
	private String flightName;
	private String fromTown;
	private String toTown;
	private Time startTime;
	private Time duration;
	private int priceBusiness;
	private int priceEconomy;

	public FlightInfoDto(String flightName, String fromTown, String toTown, Time startTime, Time duration,
						 int priceBusiness, int priceEconomy) {
		this.flightName = flightName;
		this.fromTown = fromTown;
		this.toTown = toTown;
		this.startTime = startTime;
		this.duration = duration;
		this.priceBusiness = priceBusiness;
		this.priceEconomy = priceEconomy;
	}

	public FlightInfoDto() {
	}

	@JsonGetter("priceBusiness")
	public int getPriceBusiness() {
		return priceBusiness;
	}

	@JsonSetter("priceBusiness")
	public void setPriceBusiness(int priceBusiness) {
		this.priceBusiness = priceBusiness;
	}

	@JsonGetter("priceEconomy")
	public int getPriceEconomy() {
		return priceEconomy;
	}

	@JsonSetter("priceEconomy")
	public void setPriceEconomy(int priceEconomy) {
		this.priceEconomy = priceEconomy;
	}

	@JsonGetter("flightName")
	public String getFlightName() {
		return flightName;
	}

	@JsonSetter("flightName")
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	@JsonGetter("fromTown")
	public String getFromTown() {
		return fromTown;
	}

	@JsonSetter("fromTown")
	public void setFromTown(String fromTown) {
		this.fromTown = fromTown;
	}

	@JsonGetter("toTown")
	public String getToTown() {
		return toTown;
	}

	@JsonSetter("toTown")
	public void setToTown(String toTown) {
		this.toTown = toTown;
	}

	@JsonGetter("startTime")
	public Time getStartTime() {
		return startTime;
	}

	@JsonSetter("startTime")
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@JsonGetter("duration")
	public Time getDuration() {
		return duration;
	}

	@JsonSetter("duration")
	public void setDuration(Time duration) {
		this.duration = duration;
	}
}
