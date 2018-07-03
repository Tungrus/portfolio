package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.PlaceDto;

import java.util.Set;

public class GetFreePlacesForRegistrationResponse {
	@JsonUnwrapped
	private Set<PlaceDto> placeDtos;

	public GetFreePlacesForRegistrationResponse() {
	}

	public GetFreePlacesForRegistrationResponse(Set<PlaceDto> placeDtos) {
		this.placeDtos = placeDtos;
	}

	@JsonGetter("placeDtos")
	public Set<PlaceDto> getPlaceDtos() {
		return placeDtos;
	}
	@JsonSetter("placeDtos")
	public void setPlaceDtos(Set<PlaceDto> placeDtos) {
		this.placeDtos = placeDtos;
	}
}
