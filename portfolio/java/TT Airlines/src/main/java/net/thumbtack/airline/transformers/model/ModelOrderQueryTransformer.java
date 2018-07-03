package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.OrderQueryDto;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.querties.BaseAirlineQueryParams;
import net.thumbtack.airline.querties.FlightQueryParams;
import net.thumbtack.airline.querties.OrderQuery;

import java.util.Map;

public class ModelOrderQueryTransformer {

	public static OrderQuery create(OrderQueryDto orderQueryDto, UserType userType) throws Exception {
		Map<String, Object> orderQuery = orderQueryDto.getQuery();

		for (String param : BaseAirlineQueryParams.getBaseQueryParams()) {
			if (!orderQuery.containsKey(param)) {
				orderQuery.put(param, null);
			}
		}

		if(UserType.CLIENT.equals(userType)) {
			orderQuery.put(FlightQueryParams.getApproved(), true);
		} else if (UserType.CLIENT.equals(userType)) {
			if (!orderQuery.containsKey(FlightQueryParams.getApproved())) {
				orderQuery.put(FlightQueryParams.getApproved(), null);
			}
		} else {
			throw new Exception("BAD_USER_TYPE");
		}

		return new OrderQuery(orderQuery);
	}
}
