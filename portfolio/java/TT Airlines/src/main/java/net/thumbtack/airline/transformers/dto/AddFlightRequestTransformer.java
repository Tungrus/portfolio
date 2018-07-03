package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.AddFlightRequest;
import net.thumbtack.airline.model.Flight;

public class AddFlightRequestTransformer {
	public static AddFlightRequest createFlightScheduleOnly(Flight flight) {
		return new AddFlightRequest(DateDtoTransformer.createDateDtoScheduleOnly(flight.getDates()),
				FlightInfoDtoTransformer.create(flight.getFlightInfo()), flight.getPlaneInfo().getPlaneNameType());
	}
}
