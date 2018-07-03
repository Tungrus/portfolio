package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.model.PlaneInfo;

public class ModelFlightTransformer {
	public static Flight create(FlightDto flightDto) throws BaseApplicationException {
		return new Flight(ModelPlaneTransformer.create(flightDto.getPlaneInfoDto()), create(flightDto.getFlightInfoDto()),
				ModelDateTransformer.create(flightDto.getDateDto()));
	}

	public static Flight create(FlightDto flightDto, int flightId) throws BaseApplicationException {
		return new Flight(ModelPlaneTransformer.create(flightDto.getPlaneInfoDto()), create(flightDto.getFlightInfoDto(), flightId),
				ModelDateTransformer.create(flightDto.getDateDto()));
	}

	public static Flight create(FlightInfoDto flightInfoDto, DateDto dateDto, PlaneInfo planeInfo, int flightId) throws BaseApplicationException {
		return new Flight(planeInfo, ModelFlightInfoTransformer.create(flightInfoDto, flightId),
				ModelDateTransformer.create(dateDto));
	}

	public static Flight create(FlightInfoDto flightInfoDto, DateDto dateDto, PlaneInfo planeInfo) throws BaseApplicationException {
		return new Flight(planeInfo, ModelFlightInfoTransformer.create(flightInfoDto),
				ModelDateTransformer.create(dateDto));
	}

	public static FlightInfo create(FlightInfoDto flightInfoDto) {
		return new FlightInfo(flightInfoDto.getFlightName(), flightInfoDto.getFromTown(), flightInfoDto.getToTown(),
				flightInfoDto.getStartTime(), flightInfoDto.getDuration(), flightInfoDto.getPriceBusiness(),
				flightInfoDto.getPriceEconomy());
	}

	public static FlightInfo create(FlightInfoDto flightInfoDto, int flightId) {
		return new FlightInfo(flightId, false, flightInfoDto.getFlightName(),
				flightInfoDto.getFromTown(), flightInfoDto.getToTown(), flightInfoDto.getStartTime(),
				flightInfoDto.getDuration(), flightInfoDto.getPriceBusiness(), flightInfoDto.getPriceEconomy());
	}
}
