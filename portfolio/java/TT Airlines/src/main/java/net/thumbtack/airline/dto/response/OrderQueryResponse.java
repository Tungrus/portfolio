package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.ServedOrderDto;

import java.util.Set;

public class OrderQueryResponse {
	@JsonUnwrapped
	private Set<ServedOrderDto> servedOrderDtos;

	public OrderQueryResponse() {
	}

	public OrderQueryResponse(Set<ServedOrderDto> servedOrderDtos) {
		this.servedOrderDtos = servedOrderDtos;
	}

	@JsonGetter("servedOrderDtos")
	public Set<ServedOrderDto> getServedOrderDtos() {
		return servedOrderDtos;
	}
	@JsonSetter("servedOrderDtos")
	public void setServedOrderDtos(Set<ServedOrderDto> servedOrderDtos) {
		this.servedOrderDtos = servedOrderDtos;
	}
}
