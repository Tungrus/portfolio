package net.thumbtack.airline.model;

public class Nationality {
	private int id;
	private String iso3166;
	private String name;

	public Nationality() {
	}

	public Nationality(String iso3166) {
		this.iso3166 = iso3166;
	}

	public Nationality(int id, String iso3166, String name) {
		this.id = id;
		this.iso3166 = iso3166;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
