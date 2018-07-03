package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.OrderParams;

public class ModelOrderTransformer {
	public static Order create(OrderDto orderDto, FlightInfo flightInfo, int userId) {
		return new Order(new OrderParams(0), flightInfo,
				ModelPassengersTransformer.create(orderDto.getPassengersDto()), orderDto.getOrderParamsDto().getDate(),
				userId);
	}
}