package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class FlightDto {
	@JsonUnwrapped
	private FlightInfoDto flightInfoDto;
	private PlaneInfoDto planeInfoDto;
	@JsonUnwrapped
	private DateDto dateDto;


	public FlightDto(FlightInfoDto flightInfoDto, PlaneInfoDto planeInfoDto, DateDto dateDto) {
		this.flightInfoDto = flightInfoDto;
		this.planeInfoDto = planeInfoDto;
		this.dateDto = dateDto;
	}

	public FlightDto() {
	}

	@JsonGetter("flightInfoDto")
	public FlightInfoDto getFlightInfoDto() {
		return flightInfoDto;
	}

	@JsonSetter("flightInfoDto")
	public void setFlightInfoDto(FlightInfoDto flightInfoDto) {
		this.flightInfoDto = flightInfoDto;
	}

	@JsonGetter("planeInfoDto")
	public PlaneInfoDto getPlaneInfoDto() {
		return planeInfoDto;
	}

	@JsonSetter("planeInfoDto")
	public void setPlaneInfoDto(PlaneInfoDto planeInfoDto) {
		this.planeInfoDto = planeInfoDto;
	}

	@JsonGetter("dateDto")
	public DateDto getDateDto() {
		return dateDto;
	}

	@JsonSetter("dateDto")
	public void setDateDto(DateDto dateDto) {
		this.dateDto = dateDto;
	}
}
