package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DepartureRegistrationDto {
	private int orderId;
	private int ticketId;
	private String lastName;
	private String firstName;
	private PlaceDto place;

	public DepartureRegistrationDto() {
	}

	public DepartureRegistrationDto(int orderId, int ticketId, String lastName, String firstName,
											  PlaceDto place) {
		this.orderId = orderId;
		this.ticketId = ticketId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.place = place;
	}

	@JsonGetter("orderId")
	public int getOrderId() {
		return orderId;
	}
	@JsonSetter("orderId")
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@JsonGetter("ticketId")
	public int getTicketId() {
		return ticketId;
	}
	@JsonSetter("ticketId")
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	@JsonSetter("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	@JsonSetter("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonGetter("PlaceDto")
	public PlaceDto getPlace() {
		return place;
	}
	@JsonSetter("PlaceDto")
	public void setPlace(PlaceDto place) {
		this.place = place;
	}
}
