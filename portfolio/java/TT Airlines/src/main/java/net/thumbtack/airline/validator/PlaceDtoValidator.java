package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.PlaceDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.stereotype.Component;

@Component
public class PlaceDtoValidator extends BaseValidator {

	private static String placeFieldName = "place";
	public void validate(PlaceDto placeDto, ErrorCollection errorCollection) {
		NotNullConstrain.check(placeDto.getPlace(), placeFieldName, errorCollection);
	}
}
