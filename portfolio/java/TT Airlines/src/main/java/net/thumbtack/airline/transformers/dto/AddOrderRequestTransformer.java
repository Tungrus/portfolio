package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.AddOrderRequest;
import net.thumbtack.airline.model.Order;

public class AddOrderRequestTransformer {
	public static AddOrderRequest create(Order order) {
		return new AddOrderRequest(OrderDtoTransformer.create(order));
	}

	public static AddOrderRequest createTest(Order order) {
		return new AddOrderRequest(OrderDtoTransformer.createTest(order));
	}
}
