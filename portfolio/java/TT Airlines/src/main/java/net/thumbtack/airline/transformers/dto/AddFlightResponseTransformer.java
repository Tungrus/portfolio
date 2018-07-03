package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.AddFlightResponse;
import net.thumbtack.airline.model.Flight;

public class AddFlightResponseTransformer {
	public static AddFlightResponse create(Flight flight) {
		return new AddFlightResponse(FlightDtoTransformer.create(flight), flight.getFlightInfo().getId());
	}
}
