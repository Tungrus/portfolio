package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.PlaceDto;
import net.thumbtack.airline.errors.types.runtime.BadPlaceError;
import net.thumbtack.airline.exception.BadPlaceException;
import net.thumbtack.airline.model.PlaceInPlane;
import net.thumbtack.airline.model.PlaneInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelPlaceInPlaneTransformer {

	public static PlaceInPlane create(PlaceDto placeDto, int ticketId) throws BadPlaceException {
		Pattern pattern = Pattern.compile("[\\d]*");
		Matcher matcher = pattern.matcher(placeDto.getPlace());
		if(!matcher.find()) throw new BadPlaceException(new BadPlaceError());
		int placeNumber = (int)placeDto.getPlace().substring(matcher.end()).charAt(0) - (int)'A';
		int rowNumber = Integer.valueOf(placeDto.getPlace().substring(0, matcher.end()));
		return new PlaceInPlane(ticketId, placeNumber, rowNumber, placeDto.getPlace());
	}

	public static Set<PlaceInPlane> getFreePlacesInPlane(Set<PlaceInPlane> occupiedPlaces, PlaneInfo planeInfo) {
		Set<PlaceInPlane> freePlaces = new HashSet<>();
		int rowNumber = 1;
		for(;planeInfo.getEconomyRows() + planeInfo.getBussinesRows() + 1 > rowNumber; rowNumber++) {
			for(int placeNumber = 0; planeInfo.getPlacesInEconomyRow() > placeNumber; placeNumber++) {
				PlaceInPlane placeInPlane = new PlaceInPlane(0, placeNumber, rowNumber,
						String.valueOf(rowNumber) + (char)('A' + placeNumber));
				if(!occupiedPlaces.contains(placeInPlane)) {
					freePlaces.add(placeInPlane);
				}
			}
		}
		return freePlaces;
	}
}
