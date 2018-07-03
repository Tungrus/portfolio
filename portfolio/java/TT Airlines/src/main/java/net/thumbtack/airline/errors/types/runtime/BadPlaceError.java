package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadPlaceError extends BaseError {

	public BadPlaceError() {
		super("BAD_PLACE", "place", "this place already engaged");
	}
}
