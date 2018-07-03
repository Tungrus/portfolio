package net.thumbtack.airline.model;

public class Passenger {
	private int ticketId;
	private String firstName;
	private String lastName;
	private String passportNumber;
	private String typeOfPlace;
	private Nationality nationality;
	private int price;

	public Passenger() {
	}

	public Passenger(int ticketId, String firstName, String lastName, String passportNumber, String typeOfPlace,
					 Nationality nationality, int price) {
		this.ticketId = ticketId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.typeOfPlace = typeOfPlace;
		this.nationality = nationality;
		this.price = price;
	}

	public Passenger(String firstName, String lastName, String passportNumber, String typeOfPlace, Nationality nationality) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.typeOfPlace = typeOfPlace;
		this.nationality = nationality;
		this.ticketId = 0;
		this.price = 0;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getTypeOfPlace() {
		return typeOfPlace;
	}

	public void setTypeOfPlace(String typeOfPlace) {
		this.typeOfPlace = typeOfPlace;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
