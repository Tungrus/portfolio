package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.PlaceDto;
import net.thumbtack.airline.model.PlaceInPlane;

public class PlaceDtoTransformer {
	public static PlaceDto create(PlaceInPlane placeInPlane) {
		return new PlaceDto(placeInPlane.getStringRepresentation());
	}
}
