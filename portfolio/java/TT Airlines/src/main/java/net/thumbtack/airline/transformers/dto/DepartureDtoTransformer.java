package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.DepartureRegistrationDto;
import net.thumbtack.airline.model.Departure;

public class DepartureDtoTransformer {
	public static DepartureRegistrationDto create(Departure departure) {
		return new DepartureRegistrationDto(departure.getOrderId(), departure.getTicketId(),departure.getLastName(),
				departure.getFirstName(), PlaceDtoTransformer.create(departure.getPlace()));
	}
}
