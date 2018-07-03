package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.OrderQueryDto;

public class OrderQueryRequest {
	@JsonUnwrapped
	private OrderQueryDto orderQueryDto;


	public OrderQueryRequest() {
	}

	public OrderQueryRequest(OrderQueryDto orderQueryDto) {
		this.orderQueryDto = orderQueryDto;
	}

	@JsonGetter("orderQueryDto")
	public OrderQueryDto getOrderQueryDto() {
		return orderQueryDto;
	}
	@JsonSetter("orderQueryDto")
	public void setOrderQueryDto(OrderQueryDto orderQueryDto) {
		this.orderQueryDto = orderQueryDto;
	}
}
