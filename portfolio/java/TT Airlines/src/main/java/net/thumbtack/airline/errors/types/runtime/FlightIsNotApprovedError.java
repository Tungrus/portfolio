package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class FlightIsNotApprovedError extends BaseError {



	public FlightIsNotApprovedError() {
		super("FLIGHT_IS_NOT_APPROVED", "flightId", "flight must be approved before orders");
	}
}
