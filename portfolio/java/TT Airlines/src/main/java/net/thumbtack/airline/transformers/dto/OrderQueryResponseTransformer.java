package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.ServedOrderDto;
import net.thumbtack.airline.dto.response.OrderQueryResponse;
import net.thumbtack.airline.model.Order;

import java.util.HashSet;
import java.util.Set;

public class OrderQueryResponseTransformer {
	public static OrderQueryResponse create(Set<Order> orders) {
		Set<ServedOrderDto> orderDtos = new HashSet<>();
		for(Order order : orders) {
			orderDtos.add(ServedOrderDtoTransformer.create(order));
		}
		return new OrderQueryResponse(orderDtos);
	}
}
