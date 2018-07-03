package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.model.FlightInfo;

public class FlightInfoDtoTransformer {
	public static FlightInfoDto create(FlightInfo flightInfo) {
		return new FlightInfoDto(flightInfo.getFlightName(), flightInfo.getFromTown(), flightInfo.getToTown(),
				flightInfo.getStart(), flightInfo.getDuration(), flightInfo.getPriceBusiness(),
				flightInfo.getPriceEconomy());
	}
}
