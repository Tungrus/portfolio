package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.AddOrderResponse;
import net.thumbtack.airline.model.Order;

public class AddOrderResponseTransformer {
	public static AddOrderResponse create(Order order) {
		return new AddOrderResponse(OrderDtoTransformer.create(order), order.getOrderParams().getTotalPrice(),
				order.getFlightInfo().getFlightName(), order.getFlightInfo().getFromTown(),
				order.getFlightInfo().getToTown(), order.getFlightInfo().getStart(),
				order.getFlightInfo().getDuration(), order.getOrderId());
	}
}
