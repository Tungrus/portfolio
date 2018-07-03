package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.AdminGetFlightResponse;
import net.thumbtack.airline.model.Flight;

public class AdminGetFlightResponseTransformer {
	public static AdminGetFlightResponse create(Flight flight) {
		return new AdminGetFlightResponse(DtoFlightTransformer.create(flight.getFlightInfo()),
				DtoFlightTransformer.create(flight.getPlaneInfo()), DateDtoTransformer.create(flight.getDates()),
				flight.getFlightInfo().getId(), flight.getFlightInfo().isApproved());
	}
}
