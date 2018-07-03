package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.CountriesResponse;
import net.thumbtack.airline.model.Countries;
import net.thumbtack.airline.model.Country;

public class CountriesResponseTransformer {

	public static CountriesResponse create(Countries countries) {
		CountriesResponse countriesResponse = new CountriesResponse();
		for(Country country : countries.getCountries()) {
			countriesResponse.addCountry(CountryDtoTransformer.create(country));
		}
		return countriesResponse;
	}
}
