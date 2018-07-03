package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Time;
import java.util.List;

public class ServedOrderDto extends OrderDto<ServedPassengerDto>{
	private int totalPrice;
	private String flightName;
	private String fromTown;
	private String toTown;
	private Time startTime;
	private Time duration;
	private int orderId;

	public ServedOrderDto() {
	}

	public ServedOrderDto(OrderParamsDto orderParamsDto, List<ServedPassengerDto> passengersDTO, int totalPrice,
						  String flightName, String fromTown, String toTown, Time startTime, Time duration, int orderId) {
		super(orderParamsDto, passengersDTO);
		this.totalPrice = totalPrice;
		this.flightName = flightName;
		this.fromTown = fromTown;
		this.toTown = toTown;
		this.startTime = startTime;
		this.duration = duration;
		this.orderId = orderId;
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

}
