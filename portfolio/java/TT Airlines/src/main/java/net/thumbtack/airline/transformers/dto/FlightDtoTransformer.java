package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.model.Flight;

public class FlightDtoTransformer {
	public static FlightDto create(Flight flight) {
		return new FlightDto(FlightInfoDtoTransformer.create(flight.getFlightInfo()),
				PlaneInfoDtoTransformer.create(flight.getPlaneInfo()), DateDtoTransformer.create(flight.getDates()));
	}

	public static FlightDto create(Flight flight, String specialBuild) {
		return new FlightDto(FlightInfoDtoTransformer.create(flight.getFlightInfo()),
				PlaneInfoDtoTransformer.create(flight.getPlaneInfo()),
				DateDtoTransformer.create(flight.getDates(), specialBuild));
	}
}
