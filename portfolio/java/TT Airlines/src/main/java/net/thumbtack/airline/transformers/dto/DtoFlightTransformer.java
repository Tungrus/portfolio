package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.dto.PlaneInfoDto;
import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.model.PlaneInfo;

public class DtoFlightTransformer {
	public static FlightInfoDto create(FlightInfo flightInfo) {
		return new FlightInfoDto(flightInfo.getFlightName(), flightInfo.getFromTown(), flightInfo.getToTown(),
				flightInfo.getStart(), flightInfo.getDuration(),
				flightInfo.getPriceBusiness(), flightInfo.getPriceEconomy());
	}

	public static PlaneInfoDto create(PlaneInfo planeInfo) {
		return new PlaneInfoDto(planeInfo.getPlaneNameType(), planeInfo.getBussinesRows(), planeInfo.getEconomyRows(),
				planeInfo.getPlacesInBusinessRow(), planeInfo.getPlacesInEconomyRow());
	}
}
