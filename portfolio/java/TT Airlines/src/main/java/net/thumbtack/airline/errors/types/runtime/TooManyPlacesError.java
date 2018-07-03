package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class TooManyPlacesError extends BaseError {
	public TooManyPlacesError() {
		super("TOO_MANY_PLACES", "place", "place");
	}
}
