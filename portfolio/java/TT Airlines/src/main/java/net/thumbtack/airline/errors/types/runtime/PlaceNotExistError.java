package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class PlaceNotExistError extends BaseError {

	public PlaceNotExistError() {
		super("PLACE_NOT_EXIST", "place", "this place is not exist in this plane");
	}
}
