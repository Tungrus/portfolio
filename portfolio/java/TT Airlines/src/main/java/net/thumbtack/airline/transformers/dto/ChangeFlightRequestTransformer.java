package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.ChangeFlightRequest;
import net.thumbtack.airline.model.Flight;

public class ChangeFlightRequestTransformer {
	public static ChangeFlightRequest createFlightScheduleOnly(Flight flight) {
		return new ChangeFlightRequest(DateDtoTransformer.createDateDtoScheduleOnly(flight.getDates()),
				FlightInfoDtoTransformer.create(flight.getFlightInfo()), flight.getPlaneInfo().getPlaneNameType());
	}
}
