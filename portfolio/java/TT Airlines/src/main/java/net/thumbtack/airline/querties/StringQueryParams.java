package net.thumbtack.airline.querties;

import java.util.HashSet;
import java.util.Set;

public class StringQueryParams {//TODO refact this name

	private static final String toTown = "toTown";
	private static final String fromTown = "fromTown";
	private static final String flightName = "flightName";
	private static final String planeName = "planeName";

	private static final Set<String> stringQueryParams = new HashSet<>();


	static {
		stringQueryParams.add(getFlightName());
		stringQueryParams.add(getFromTown());
		stringQueryParams.add(getPlaneName());
		stringQueryParams.add(getToTown());
	}
	public static String getToTown() {
		return toTown;
	}

	public static String getFromTown() {
		return fromTown;
	}

	public static String getFlightName() {
		return flightName;
	}

	public static String getPlaneName() {
		return planeName;
	}

	public static Set<String> getStringQueryParams() {
		return stringQueryParams;
	}
}
