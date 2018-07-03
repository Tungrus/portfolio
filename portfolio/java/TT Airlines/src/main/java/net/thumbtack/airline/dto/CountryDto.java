package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CountryDto {
	private String iso;
	private String name;

	public CountryDto() {
	}

	public CountryDto(String iso, String name) {
		this.iso = iso;
		this.name = name;
	}

	@JsonGetter("iso")
	public String getIso() {
		return iso;
	}

	@JsonSetter("iso")
	public void setIso(String iso) {
		this.iso = iso;
	}

	@JsonGetter("name")
	public String getName() {
		return name;
	}

	@JsonSetter("name")
	public void setName(String name) {
		this.name = name;
	}
}
