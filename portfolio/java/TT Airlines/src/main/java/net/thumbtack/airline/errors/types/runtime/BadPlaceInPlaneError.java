package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadPlaceInPlaneError extends BaseError{

	public BadPlaceInPlaneError(String field) {
		super(field.toUpperCase() + "_PLACE_ALREADY_ENGAGED", field,
				field + " place must be free to choose");
	}
}
