package net.thumbtack.airline.model;

import java.util.Set;

public class Countries {
	private Set<Country> countries;

	public Countries() {
	}

	public Countries(Set<Country> countries) {
		this.countries = countries;
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}
}
