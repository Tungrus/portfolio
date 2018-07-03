package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.dto.OrderParamsDto;
import net.thumbtack.airline.dto.ServedPassengerDto;

import java.sql.Time;
import java.util.List;

public class AddOrderResponse {
	@JsonIgnore
	private OrderDto<ServedPassengerDto> orderDto;
	private int totalPrice;
	private String flightName;
	private String fromTown;
	private String toTown;
	private Time startTime;
	private Time duration;
	private int orderId;

	public AddOrderResponse() {
		orderDto = new OrderDto<>();
	}

	public AddOrderResponse(OrderDto orderDto, int totalPrice, String flightName, String fromTown, String toTown,
							Time startTime, Time duration, int orderId) {
		this.orderDto = orderDto;
		this.totalPrice = totalPrice;
		this.flightName = flightName;
		this.fromTown = fromTown;
		this.toTown = toTown;
		this.startTime = startTime;
		this.duration = duration;
		this.orderId = orderId;
	}

	@JsonGetter("orderDto")
	public OrderDto getOrderDto() {
		return orderDto;
	}
	@JsonSetter("orderDto")
	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}

	@JsonGetter("totalPrice")
	public int getTotalPrice() {
		return totalPrice;
	}
	@JsonSetter("totalPrice")
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@JsonGetter("flightName")
	public String getFlightName() {
		return flightName;
	}
	@JsonSetter("flightName")
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	@JsonGetter("fromTown")
	public String getFromTown() {
		return fromTown;
	}
	@JsonSetter("fromTown")
	public void setFromTown(String fromTown) {
		this.fromTown = fromTown;
	}

	@JsonGetter("toTown")
	public String getToTown() {
		return toTown;
	}
	@JsonSetter("toTown")
	public void setToTown(String toTown) {
		this.toTown = toTown;
	}

	@JsonGetter("startTime")
	public Time getStartTime() {
		return startTime;
	}
	@JsonSetter("startTime")
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@JsonGetter("duration")
	public Time getDuration() {
		return duration;
	}
	@JsonSetter("duration")
	public void setDuration(Time duration) {
		this.duration = duration;
	}

	@JsonGetter("orderId")
	public int getOrderId() {
		return orderId;
	}
	@JsonSetter("orderId")
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@JsonSetter("passengers")
	public void setPassengers(List<ServedPassengerDto> passengers) {
		this.orderDto.setPassengersDto(passengers);
	}
	@JsonGetter("passengers")
	public List<ServedPassengerDto> getPassengers() {
		return this.orderDto.getPassengersDto();
	}

	@JsonGetter("orderParamsDto")
	public OrderParamsDto getOrderParamsDto() {
		return orderDto.getOrderParamsDto();
	}
	@JsonSetter("orderParamsDto")
	public void setOrderParamsDto(OrderParamsDto orderParamsDto) {
		this.orderDto.setOrderParamsDto(orderParamsDto);
	}

}
