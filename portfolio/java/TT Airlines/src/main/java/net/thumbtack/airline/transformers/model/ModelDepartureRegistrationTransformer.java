package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.DepartureRegistrationDto;
import net.thumbtack.airline.exception.BadPlaceException;
import net.thumbtack.airline.model.Departure;

public class ModelDepartureRegistrationTransformer {
	public static Departure create(DepartureRegistrationDto departureRegistrationDto) throws BadPlaceException {
		return new Departure(departureRegistrationDto.getOrderId(), departureRegistrationDto.getTicketId(),
				ModelPlaceInPlaneTransformer.create(departureRegistrationDto.getPlace(),
						departureRegistrationDto.getTicketId()),
						departureRegistrationDto.getLastName(),
						departureRegistrationDto.getFirstName());
	}
}

