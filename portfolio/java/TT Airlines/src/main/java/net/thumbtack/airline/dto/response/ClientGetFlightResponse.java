package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.FlightInfoDto;

public class ClientGetFlightResponse {
	@JsonUnwrapped
	private FlightInfoDto flightInfoDto;

	private int flightId;

	public ClientGetFlightResponse(FlightInfoDto flightInfoDto, int flightId) {
		this.flightInfoDto = flightInfoDto;
		this.flightId = flightId;
	}

	public FlightInfoDto getFlightInfoDto() {
		return flightInfoDto;
	}

	public void setFlightInfoDto(FlightInfoDto flightInfoDto) {
		this.flightInfoDto = flightInfoDto;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
}
