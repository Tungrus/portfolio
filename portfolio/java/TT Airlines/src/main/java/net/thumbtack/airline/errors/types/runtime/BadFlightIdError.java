package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadFlightIdError extends BaseError {
	public BadFlightIdError() {
		super("BAD_FLIGHT_ID", "flightId", "flight id not found in database");
	}
}
