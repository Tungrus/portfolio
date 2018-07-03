package net.thumbtack.airline.querties;

import java.util.HashSet;
import java.util.Set;

public class FlightQueryParams extends BaseAirlineQueryParams {
	private static final String approved = "isApproved";

	private static final Set<String> flightQueryParams = new HashSet<>();

	static {
		flightQueryParams.add(approved);
	}

	public static String getApproved() {
		return approved;
	}

	public static Set<String> getFlightParams() {
		return flightQueryParams;
	}
}
