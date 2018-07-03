package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.DepartureRegistrationDto;
import net.thumbtack.airline.dto.PlaceDto;
import net.thumbtack.airline.dto.ServedPassengerDto;
import net.thumbtack.airline.dto.requests.DepartureClientRegistrationRequest;

public class DepartureClientRegistrationRequestTransformer {
	public static DepartureClientRegistrationRequest create(int orderId, ServedPassengerDto servedPassengerDto,
															PlaceDto placeDto) {
		return new DepartureClientRegistrationRequest(
				new DepartureRegistrationDto(orderId, servedPassengerDto.getTicket(), servedPassengerDto.getLastName(),
						servedPassengerDto.getFirstName(), placeDto));
	}
}
