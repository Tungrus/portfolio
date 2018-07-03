package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.model.Nationality;

public class ModelNationalityTransformer {
	public static Nationality create(String iso3166) {
		return new Nationality(0, iso3166, null);
	}
}
