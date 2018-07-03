package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import net.thumbtack.airline.dto.CountryDto;

import java.util.HashSet;
import java.util.Set;

public class CountriesResponse {
	private Set<CountryDto> countryDtos;

	public CountriesResponse(Set<CountryDto> countryDtos) {
		this.countryDtos = countryDtos;
	}

	public CountriesResponse() {
		countryDtos = new HashSet<>();
	}

	@JsonSetter("countryDtos")
	public Set<CountryDto> getCountryDtos() {
		return countryDtos;
	}

	@JsonGetter("countryDtos")
	public void setCountryDtos(Set<CountryDto> countryDtos) {
		this.countryDtos = countryDtos;
	}

	public void addCountry(CountryDto countryDto) {
		countryDtos.add(countryDto);
	}
}
