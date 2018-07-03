package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.PlaceDto;
import net.thumbtack.airline.dto.response.GetFreePlacesForRegistrationResponse;
import net.thumbtack.airline.model.PlaceInPlane;

import java.util.HashSet;
import java.util.Set;

public class GetFreePlacesForRegistrationResponseTransformer {
	public static GetFreePlacesForRegistrationResponse create(Set<PlaceInPlane> placeInPlanes) {
		Set<PlaceDto> placeDtos = new HashSet<>();
		for(PlaceInPlane placeInPlane : placeInPlanes) {
			placeDtos.add(PlaceDtoTransformer.create(placeInPlane));
		}
		return new GetFreePlacesForRegistrationResponse(placeDtos);
	}
}
