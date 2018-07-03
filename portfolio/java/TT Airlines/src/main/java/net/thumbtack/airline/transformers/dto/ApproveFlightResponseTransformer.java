package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.ApproveFlightResponse;
import net.thumbtack.airline.model.Flight;

public class ApproveFlightResponseTransformer {
	public static ApproveFlightResponse create(Flight flight) {
		return new ApproveFlightResponse(DtoFlightTransformer.create(flight.getFlightInfo()),
				DtoFlightTransformer.create(flight.getPlaneInfo()), DateDtoTransformer.create(flight.getDates()),
				flight.getFlightInfo().getId());
	}
}
