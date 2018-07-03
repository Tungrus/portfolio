package net.thumbtack.airline.querties;

import java.util.HashSet;
import java.util.Set;

public class BaseAirlineQueryParams {


	private static final Set<String> baseQueryParams = new HashSet<>();

	static {
		baseQueryParams.add(BaseAirlineQueryParams.getFlightName());
		baseQueryParams.add(BaseAirlineQueryParams.getFromTown());
		baseQueryParams.add(BaseAirlineQueryParams.getToTown());
		baseQueryParams.add(BaseAirlineQueryParams.getPlaneName());
		baseQueryParams.add(BaseAirlineQueryParams.getToDate());
		baseQueryParams.add(BaseAirlineQueryParams.getFromDate());
	}

	public static String getToTown() {
		return StringQueryParams.getToTown();
	}

	public static String getFromTown() {
		return StringQueryParams.getFromTown();
	}

	public static String getFlightName() {
		return StringQueryParams.getFlightName();
	}

	public static String getPlaneName() {
		return StringQueryParams.getPlaneName();
	}

	public static String getFromDate() {
		return DatesQueryParams.getFromDate();
	}

	public static String getToDate() {
		return DatesQueryParams.getToDate();
	}

	public static Set<String> getBaseQueryParams() {
		return baseQueryParams;
	}
}
