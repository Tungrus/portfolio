package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import net.thumbtack.airline.dto.DepartureRegistrationDto;

public class DepartureClientRegistrationRequest {
	private DepartureRegistrationDto registrationDto;

	public DepartureClientRegistrationRequest() {
	}

	public DepartureClientRegistrationRequest(DepartureRegistrationDto registrationDto) {
		this.registrationDto = registrationDto;
	}

	@JsonGetter("registrationDto")
	public DepartureRegistrationDto getRegistrationDto() {
		return registrationDto;
	}
	@JsonSetter("registrationDto")
	public void setRegistrationDto(DepartureRegistrationDto registrationDto) {
		this.registrationDto = registrationDto;
	}
}
