package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ServedPassengerDto extends PassengerDto {
	private int ticket;
	private int price;

	public ServedPassengerDto() {
	}

	public ServedPassengerDto(String firstName, String lastName, String nationality, String passportNumber,
							  String typeOfPlace, int ticket, int price) {
		super(firstName, lastName, nationality, passportNumber, typeOfPlace);
		this.ticket = ticket;
		this.price = price;
	}

	@JsonGetter("ticket")
	public int getTicket() {
		return ticket;
	}
	@JsonSetter("ticket")
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	@JsonGetter("price")
	public int getPrice() {
		return price;
	}
	@JsonSetter("price")
	public void setPrice(int price) {
		this.price = price;
	}
}
