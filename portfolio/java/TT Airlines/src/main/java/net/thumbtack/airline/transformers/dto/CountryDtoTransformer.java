package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.CountryDto;
import net.thumbtack.airline.model.Country;

public class CountryDtoTransformer {
	public static CountryDto create(Country country) {
		return new CountryDto(country.getIso3166(), country.getName());
	}
}
