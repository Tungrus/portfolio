package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PlaceDto {
	private String place;

	public PlaceDto() {
	}

	public PlaceDto(String place) {
		this.place = place;
	}

	@JsonGetter("place")
	public String getPlace() {
		return place;
	}
	@JsonSetter("place")
	public void setPlace(String place) {
		this.place = place;
	}
}
