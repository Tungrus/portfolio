package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.OrderQueryDto;
import net.thumbtack.airline.dto.requests.OrderQueryRequest;

import java.util.Map;

public class OrderQueryRequestTransformer {
	public static OrderQueryRequest create(Map<String, Object> map) {
		return new OrderQueryRequest(new OrderQueryDto(map));
	}
}
