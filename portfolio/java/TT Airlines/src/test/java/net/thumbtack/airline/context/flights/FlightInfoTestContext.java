package net.thumbtack.airline.context.flights;

import net.thumbtack.airline.model.FlightInfo;

import java.sql.Time;

public class FlightInfoTestContext {
	private String flightNameDaily = "place1_place2_Daily";
	private String flightNameOdd = "place1_place2_Odd";
	private String flightNameEven = "place1_place2_Even";
	private String flightNameByDayOfWeek = "place1_place2_ByDayOfWeek";
	private String flightNameByDaysOfMonth = "place1_place2_ByDaysOfMonth";

	private String fromTown = "place1";
	private String toTown = "place2";

	private Time startTime = new Time(12, 12, 0);
	private Time duration = new Time(5, 0, 0);

	private int priceBuisness = 1000;
	private int priceEconomy = 100;

	private FlightInfo flightInfoDaily = new FlightInfo(flightNameDaily, fromTown, toTown, startTime,
			duration, priceBuisness, priceEconomy);
	private FlightInfo flightInfoEven = new FlightInfo(flightNameEven, fromTown, toTown, startTime,
			duration, priceBuisness, priceEconomy);
	private FlightInfo flightInfoOdd = new FlightInfo(flightNameOdd, fromTown, toTown, startTime,
			duration, priceBuisness, priceEconomy);
	private FlightInfo flightInfoDayOfWeek = new FlightInfo(flightNameByDayOfWeek, fromTown, toTown, startTime,
			duration, priceBuisness, priceEconomy);
	private FlightInfo flightInfoDaysOfMonth = new FlightInfo(flightNameByDaysOfMonth, fromTown, toTown, startTime,
			duration, priceBuisness, priceEconomy);


	public FlightInfoTestContext() {
	}


	public String getFlightNameDaily() {
		return flightNameDaily;
	}

	public void setFlightNameDaily(String flightNameDaily) {
		this.flightNameDaily = flightNameDaily;
	}

	public String getFlightNameOdd() {
		return flightNameOdd;
	}

	public void setFlightNameOdd(String flightNameOdd) {
		this.flightNameOdd = flightNameOdd;
	}

	public String getFlightNameEven() {
		return flightNameEven;
	}

	public void setFlightNameEven(String flightNameEven) {
		this.flightNameEven = flightNameEven;
	}

	public String getFlightNameByDayOfWeek() {
		return flightNameByDayOfWeek;
	}

	public void setFlightNameByDayOfWeek(String flightNameByDayOfWeek) {
		this.flightNameByDayOfWeek = flightNameByDayOfWeek;
	}

	public String getFlightNameByDaysOfMonth() {
		return flightNameByDaysOfMonth;
	}

	public void setFlightNameByDaysOfMonth(String flightNameByDaysOfMonth) {
		this.flightNameByDaysOfMonth = flightNameByDaysOfMonth;
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public int getPriceBuisness() {
		return priceBuisness;
	}

	public void setPriceBuisness(int priceBuisness) {
		this.priceBuisness = priceBuisness;
	}

	public int getPriceEconomy() {
		return priceEconomy;
	}

	public void setPriceEconomy(int priceEconomy) {
		this.priceEconomy = priceEconomy;
	}

	public FlightInfo getFlightInfoDaily() {
		return flightInfoDaily;
	}

	public void setFlightInfoDaily(FlightInfo flightInfoDaily) {
		this.flightInfoDaily = flightInfoDaily;
	}

	public FlightInfo getFlightInfoEven() {
		return flightInfoEven;
	}

	public void setFlightInfoEven(FlightInfo flightInfoEven) {
		this.flightInfoEven = flightInfoEven;
	}

	public FlightInfo getFlightInfoOdd() {
		return flightInfoOdd;
	}

	public void setFlightInfoOdd(FlightInfo flightInfoOdd) {
		this.flightInfoOdd = flightInfoOdd;
	}

	public FlightInfo getFlightInfoDayOfWeek() {
		return flightInfoDayOfWeek;
	}

	public void setFlightInfoDayOfWeek(FlightInfo flightInfoDayOfWeek) {
		this.flightInfoDayOfWeek = flightInfoDayOfWeek;
	}

	public FlightInfo getFlightInfoDaysOfMonth() {
		return flightInfoDaysOfMonth;
	}

	public void setFlightInfoDaysOfMonth(FlightInfo flightInfoDaysOfMonth) {
		this.flightInfoDaysOfMonth = flightInfoDaysOfMonth;
	}
}
