package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.FlightDto;

public class AddFlightResponse {
	@JsonUnwrapped
	private FlightDto flightDto;
	private int flightId;


	public AddFlightResponse(FlightDto flightDto, int flightId) {
		this.flightDto = flightDto;
		this.flightId = flightId;
	}

	public AddFlightResponse() {
	}

	@JsonGetter("flightDto")
	public FlightDto getFlightDto() {
		return flightDto;
	}

	@JsonSetter("flightDto")
	public void setFlightDto(FlightDto flightDto) {
		this.flightDto = flightDto;
	}

	@JsonGetter("flightId")
	public int getFlightId() {
		return flightId;
	}

	@JsonSetter("flightId")
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
}
