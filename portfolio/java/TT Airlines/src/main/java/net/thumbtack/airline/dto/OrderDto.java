package net.thumbtack.airline.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class OrderDto<Passenger extends PassengerDto> {
	@JsonUnwrapped
	private OrderParamsDto orderParamsDto;
	@JsonUnwrapped
	private List<Passenger> passengersDto;

	public OrderDto() {
	}

	public OrderDto(OrderParamsDto orderParamsDto, List<Passenger> passengersDto) {
		this.orderParamsDto = orderParamsDto;
		this.passengersDto = passengersDto;
	}

	@JsonGetter("orderParamsDto")
	public OrderParamsDto getOrderParamsDto() {
		return orderParamsDto;
	}
	@JsonSetter("orderParamsDto")
	public void setOrderParamsDto(OrderParamsDto orderParamsDto) {
		this.orderParamsDto = orderParamsDto;
	}

	@JsonGetter("passengersDto")
	public List<Passenger> getPassengersDto() {
		return passengersDto;
	}
	@JsonSetter("passengersDto")
	public void setPassengersDto(List<Passenger> passengersDto) {
		this.passengersDto = passengersDto;
	}

	public int getPlacesInBusinessRow() {
		return getPlacesByType("BUSINESS");
	}

	public int getPlacesInEconomyRow() {
		return getPlacesByType("ECONOMY");
	}

	private int getPlacesByType(String type) {
		int count = 0;
		for(Passenger passenger : passengersDto) {
			if(passenger.getTypeOfPlace().equals(type)) {
				count++;
			}
		}
		return count;
	}
}
