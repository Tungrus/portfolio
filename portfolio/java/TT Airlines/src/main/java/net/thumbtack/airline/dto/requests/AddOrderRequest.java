package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.OrderDto;

public class AddOrderRequest {
	@JsonUnwrapped
	private OrderDto orderDto;

	public AddOrderRequest(OrderDto orderDto) {
		this.orderDto = orderDto;
	}

	public AddOrderRequest() {
	}

	@JsonGetter("orderDto")
	public OrderDto getOrderDto() {
		return orderDto;
	}
	@JsonSetter("orderDto")
	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}
}
