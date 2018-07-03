package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.PassengerDto;
import net.thumbtack.airline.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class ModelPassengersTransformer {
	public static Passenger create(PassengerDto passengerDto) {
		return new Passenger(passengerDto.getFirstName(), passengerDto.getLastName(), passengerDto.getPassportNumber(),
				passengerDto.getTypeOfPlace(),
				ModelNationalityTransformer.create(passengerDto.getNationality()));
	}

	public static List<Passenger> create(List<PassengerDto> passengerDtos) {
		List<Passenger> passengers = new ArrayList<>();
		for(PassengerDto passengerDto : passengerDtos) {
			passengers.add(create(passengerDto));
		}
		return passengers;
	}
}
