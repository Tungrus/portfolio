package net.thumbtack.airline.querties;

import java.util.HashSet;
import java.util.Set;

public class OrderQueryParams extends BaseAirlineQueryParams{
	private static final String clientId = "clientId";

	private static final Set<String> orderQueryParams = new HashSet<>();

	static {
		orderQueryParams.add(clientId);
	}

	public static String getClientId() {
		return clientId;
	}

	public static Set<String> getOrderQueryParams() {
		return orderQueryParams;
	}
}
