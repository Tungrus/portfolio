package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.DepartureClientRegistrationResponse;
import net.thumbtack.airline.model.Departure;

public class DepartureClientRegistrationResponseTransformer {
	public static DepartureClientRegistrationResponse create(Departure departure) {
		return new DepartureClientRegistrationResponse(DepartureDtoTransformer.create(departure));
	}
}
