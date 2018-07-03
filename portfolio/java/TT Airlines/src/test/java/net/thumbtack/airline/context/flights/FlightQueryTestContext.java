package net.thumbtack.airline.context.flights;

import net.thumbtack.airline.context.dates.DatesTestContext;
import net.thumbtack.airline.dto.FlightsQueryDto;
import net.thumbtack.airline.querties.FlightQueryParams;

import java.util.Map;
import java.util.TreeMap;

public class FlightQueryTestContext {
	private DatesTestContext datesTestContext = new DatesTestContext();
	private FlightInfoTestContext flightInfoTestContext = new FlightInfoTestContext();
	private PlaneTestContext planeTestContext = new PlaneTestContext();

	public FlightQueryTestContext() {

	}

	public FlightsQueryDto getFlightQueryDTO() {
		Map<String, Object> query = new TreeMap<>();
		query.put(FlightQueryParams.getToDate(), datesTestContext.getToDate());
		query.put(FlightQueryParams.getFromDate(), datesTestContext.getFromDate());
		query.put(FlightQueryParams.getPlaneName(), planeTestContext.getPlane1().getPlaneInfo().getPlaneNameType());
		query.put(FlightQueryParams.getFromTown(), flightInfoTestContext.getFromTown());
		query.put(FlightQueryParams.getToTown(), flightInfoTestContext.getToTown());
		query.put(FlightQueryParams.getFlightName(), flightInfoTestContext.getFlightNameDaily());
		return new FlightsQueryDto(query);
	}
}
