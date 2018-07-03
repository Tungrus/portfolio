package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import net.thumbtack.airline.dto.DepartureRegistrationDto;

public class DepartureClientRegistrationResponse {
	private DepartureRegistrationDto departureRegistrationDto;

	public DepartureClientRegistrationResponse() {
	}

	public DepartureClientRegistrationResponse(DepartureRegistrationDto departureRegistrationDto) {
		this.departureRegistrationDto = departureRegistrationDto;
	}

	@JsonGetter("departureRegistrationDto")
	public DepartureRegistrationDto getDepartureRegistrationDto() {
		return departureRegistrationDto;
	}
	@JsonSetter("departureRegistrationDto")
	public void setDepartureRegistrationDto(DepartureRegistrationDto departureRegistrationDto) {
		this.departureRegistrationDto = departureRegistrationDto;
	}
}
