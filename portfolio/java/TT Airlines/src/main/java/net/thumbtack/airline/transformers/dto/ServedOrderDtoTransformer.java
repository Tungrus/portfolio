package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.ServedOrderDto;
import net.thumbtack.airline.model.Order;

public class ServedOrderDtoTransformer {
	public static ServedOrderDto create(Order order) {
		return new ServedOrderDto(OrderParamsDtoTransformer.create(order),
				ServedPassengersDtoTransformer.create(order.getPassengers()), order.getOrderParams().getTotalPrice(),
				order.getFlightInfo().getFlightName(), order.getFlightInfo().getFromTown(),
				order.getFlightInfo().getToTown(), order.getFlightInfo().getStart(),
				order.getFlightInfo().getDuration(),order.getOrderId());
	}
}
