package net.thumbtack.airline.querties;

import java.util.HashSet;
import java.util.Set;

public class DatesQueryParams {
	private static final String fromDate = "fromDate";
	private static final String toDate = "toDate";

	private static final Set<String> dateQueryParams = new HashSet<>();

	static {
		dateQueryParams.add(fromDate);
		dateQueryParams.add( toDate);
	}

	public static String getFromDate() {
		return fromDate;
	}

	public static String getToDate() {
		return toDate;
	}

	public static Set<String> getBaseQueryParams() {
		return dateQueryParams;
	}
}
