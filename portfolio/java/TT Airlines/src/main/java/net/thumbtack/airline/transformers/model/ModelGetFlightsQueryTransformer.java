package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.FlightsQueryDto;
import net.thumbtack.airline.errors.types.runtime.UnresolvedUserTypeError;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.querties.BaseAirlineQueryParams;
import net.thumbtack.airline.querties.FlightQueryParams;
import net.thumbtack.airline.querties.FlightsQuery;

import java.util.Map;

public class ModelGetFlightsQueryTransformer {

	public static FlightsQuery create(FlightsQueryDto flightsQueryDto, UserType userType) throws Exception {
		Map query = flightsQueryDto.getQuery();
		for (String param : BaseAirlineQueryParams.getBaseQueryParams()) {
			if (!query.containsKey(param)) {
				query.put(param, null);
			}
		}

		if(UserType.CLIENT.equals(userType)) {
			query.put(FlightQueryParams.getApproved(), true);
		} else if (UserType.ADMIN.equals(userType)) {
			if (!query.containsKey(FlightQueryParams.getApproved())) {
				query.put(FlightQueryParams.getApproved(), null);
			}
		} else {
			throw new DataNotFoundException( new UnresolvedUserTypeError());
		}

		return new FlightsQuery(query);
	}
}
