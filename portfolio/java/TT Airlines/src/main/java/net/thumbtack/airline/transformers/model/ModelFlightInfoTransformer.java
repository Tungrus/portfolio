package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.model.FlightInfo;

public class ModelFlightInfoTransformer {
	public static FlightInfo create(FlightInfoDto flightInfoDto) {
		return new FlightInfo(flightInfoDto.getFlightName(), flightInfoDto.getFromTown(), flightInfoDto.getToTown(),
				flightInfoDto.getStartTime(), flightInfoDto.getDuration(), flightInfoDto.getPriceBusiness(),
				flightInfoDto.getPriceEconomy());
	}

	public static FlightInfo create(FlightInfoDto flightInfoDto, int flightId) {
		return new FlightInfo(flightId, false, flightInfoDto.getFlightName(), flightInfoDto.getFromTown(), flightInfoDto.getToTown(),
				flightInfoDto.getStartTime(), flightInfoDto.getDuration(), flightInfoDto.getPriceBusiness(),
				flightInfoDto.getPriceEconomy());
	}
}
