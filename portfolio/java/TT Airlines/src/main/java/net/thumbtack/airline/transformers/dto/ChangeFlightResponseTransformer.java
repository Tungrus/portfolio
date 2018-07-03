package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.ChangeFlightResponse;
import net.thumbtack.airline.model.Flight;

public class ChangeFlightResponseTransformer {
	public static ChangeFlightResponse create(Flight flight) {
		return new ChangeFlightResponse(FlightDtoTransformer.create(flight), flight.getFlightInfo().getId());
	}

	public static ChangeFlightResponse create(Flight flight, String specialBuild) {
		return new ChangeFlightResponse(FlightDtoTransformer.create(flight), flight.getFlightInfo().getId());
	}
}
