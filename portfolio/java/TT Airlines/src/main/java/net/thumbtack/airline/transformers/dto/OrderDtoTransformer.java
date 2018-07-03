package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.model.Order;

public class OrderDtoTransformer {
	public static OrderDto create(Order order) {
		return new OrderDto(OrderParamsDtoTransformer.create(order),
				ServedPassengersDtoTransformer.create(order.getPassengers()));
	}

	public static OrderDto createTest(Order order) {
		return new OrderDto(OrderParamsDtoTransformer.create(order),
				ServedPassengersDtoTransformer.createTest(order.getPassengers()));
	}
}
