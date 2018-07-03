package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.PassengerDto;
import net.thumbtack.airline.dto.ServedPassengerDto;
import net.thumbtack.airline.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class ServedPassengersDtoTransformer {
	public static ServedPassengerDto create(Passenger passenger) {
		return new ServedPassengerDto(passenger.getFirstName(), passenger.getLastName(),
				NationalityDtoTransformer.create(passenger.getNationality()), passenger.getPassportNumber(),
				passenger.getTypeOfPlace(), passenger.getTicketId(), passenger.getPrice());
	}

	public static List<ServedPassengerDto> create(List<Passenger> passengers) {
		List<ServedPassengerDto> servedPassengers = new ArrayList<>();
		for(Passenger passenger : passengers) {
			servedPassengers.add(create(passenger));
		}
		return servedPassengers;
	}

	public static List<PassengerDto> createTest(List<Passenger> list) {
		List<PassengerDto> result = new ArrayList<>();
		for(Passenger passenger : list) {
			result.add(createTest(passenger));
		}
		return result;
	}

	public static PassengerDto createTest(Passenger passenger) {
		return new PassengerDto(passenger.getFirstName(), passenger.getLastName(),
				passenger.getNationality().getIso3166(),
				passenger.getPassportNumber(), passenger.getTypeOfPlace());
	}
}
