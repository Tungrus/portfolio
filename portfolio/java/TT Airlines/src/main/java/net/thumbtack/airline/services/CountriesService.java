package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.CountriesDao;
import net.thumbtack.airline.dto.response.CountriesResponse;
import net.thumbtack.airline.transformers.dto.CountriesResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountriesService {
	@Autowired
	private CountriesDao countriesDao;

	public CountriesResponse getCountries() {
		return CountriesResponseTransformer.create(countriesDao.getCountries());
	}
}

