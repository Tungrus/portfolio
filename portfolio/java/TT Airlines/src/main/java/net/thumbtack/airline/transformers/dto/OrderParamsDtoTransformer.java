package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.OrderParamsDto;
import net.thumbtack.airline.model.Order;

public class OrderParamsDtoTransformer {
	public static OrderParamsDto create(Order order) {
		return new OrderParamsDto(order.getFlightInfo().getId(), order.getFlightDate());
	}
}
