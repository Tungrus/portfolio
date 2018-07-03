package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.model.Nationality;

public class NationalityDtoTransformer {
	public static String create(Nationality nationality) {
		return nationality.getIso3166() + "-" + nationality.getName();
	}
}
