package net.thumbtack.airline.model;

public class Departure {
	private int orderId;
	private int ticketId;
	private PlaceInPlane place;
	private String lastName;
	private String firstName;

	public Departure() {
	}

	public Departure(int orderId, int ticketId, PlaceInPlane place, String lastName, String firstName) {
		this.orderId = orderId;
		this.ticketId = ticketId;
		this.place = place;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public PlaceInPlane getPlace() {
		return place;
	}

	public void setPlace(PlaceInPlane place) {
		this.place = place;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Departure)) return false;

		Departure departure = (Departure) o;

		if (getOrderId() != departure.getOrderId()) return false;
		if (getTicketId() != departure.getTicketId()) return false;
		if (!getPlace().equals(departure.getPlace())) return false;
		if (!getLastName().equals(departure.getLastName())) return false;
		return getFirstName().equals(departure.getFirstName());
	}

	@Override
	public int hashCode() {
		int result = getOrderId();
		result = 31 * result + getTicketId();
		result = 31 * result + getPlace().hashCode();
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + getFirstName().hashCode();
		return result;
	}
}
