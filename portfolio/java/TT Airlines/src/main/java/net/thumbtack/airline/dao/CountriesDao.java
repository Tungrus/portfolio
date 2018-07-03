package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Countries;
import net.thumbtack.airline.model.Nationality;

public interface CountriesDao {
	public Countries getCountries();
	public Nationality getCountryName(Nationality nationality) throws DataNotFoundException;
}
