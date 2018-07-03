package net.thumbtack.airline.model;

public class Country {
	private String iso3166;
	private String name;

	public Country() {
	}

	public Country(String iso3166, String name) {
		this.iso3166 = iso3166;
		this.name = name;
	}

	public String getIso3166() {
		return iso3166;
	}

	public void setIso3166(String iso3166) {
		this.iso3166 = iso3166;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Country)) return false;

		Country country = (Country) o;

		if (!getIso3166().equals(country.getIso3166())) return false;
		return getName().equals(country.getName());
	}

	@Override
	public int hashCode() {
		int result = getIso3166().hashCode();
		result = 31 * result + getName().hashCode();
		return result;
	}
}
