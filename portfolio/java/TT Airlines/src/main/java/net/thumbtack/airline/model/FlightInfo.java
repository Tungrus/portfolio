package net.thumbtack.airline.model;

import java.sql.Time;

public class FlightInfo {
	private int id;
	private boolean approved;
	private String flightName;
	private String fromTown;
	private String toTown;
	private Time start;
	private Time duration;
	private int priceBusiness;
	private int priceEconomy;

	public FlightInfo(int id, boolean approved, String flightName, String fromTown, String toTown,
					  Time start, Time duration, int priceBusiness, int priceEconomy) {
		this.id = id;
		this.approved = approved;
		this.flightName = flightName;
		this.fromTown = fromTown;
		this.toTown = toTown;
		this.start = start;
		this.duration = duration;
		this.priceBusiness = priceBusiness;
		this.priceEconomy = priceEconomy;
	}

	public FlightInfo(String flightName, String fromTown, String toTown, Time start, Time duration,
					  int priceBusiness, int priceEconomy) {
		this.flightName = flightName;
		this.fromTown = fromTown;
		this.toTown = toTown;
		this.start = start;
		this.duration = duration;
		this.priceBusiness = priceBusiness;
		this.priceEconomy = priceEconomy;
		this.id = 0;
		this.approved = false;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFromTown() {
		return fromTown;
	}

	public void setFromTown(String fromTown) {
		this.fromTown = fromTown;
	}

	public String getToTown() {
		return toTown;
	}

	public void setToTown(String toTown) {
		this.toTown = toTown;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getPriceBusiness() {
		return priceBusiness;
	}

	public void setPriceBusiness(int priceBusiness) {
		this.priceBusiness = priceBusiness;
	}

	public int getPriceEconomy() {
		return priceEconomy;
	}

	public void setPriceEconomy(int priceEconomy) {
		this.priceEconomy = priceEconomy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
