package net.thumbtack.airline.model;

import net.thumbtack.airline.utils.dates.Dates;

public class Flight {
	private PlaneInfo planeInfo;
	private FlightInfo flightInfo;
	private Dates dates;

	public Flight(PlaneInfo planeInfo, FlightInfo flightInfo, Dates dates) {
		this.planeInfo = planeInfo;
		this.flightInfo = flightInfo;
		this.dates = dates;
	}

	public PlaneInfo getPlaneInfo() {
		return planeInfo;
	}

	public void setPlaneInfo(PlaneInfo planeInfo) {
		this.planeInfo = planeInfo;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public Dates getDates() {
		return dates;
	}

	public void setDates(Dates dates) {
		this.dates = dates;
	}
}
