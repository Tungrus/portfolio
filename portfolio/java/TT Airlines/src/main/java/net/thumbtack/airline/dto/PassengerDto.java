package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PassengerDto {
	private String nationality;
	private String firstName;
	private String lastName;
	private String passportNumber;
	private String typeOfPlace;


	public PassengerDto() {
	}

	public PassengerDto(String firstName, String lastName, String nationality, String passportNumber,
						String typeOfPlace) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.passportNumber = passportNumber;
		this.typeOfPlace = typeOfPlace;
	}

	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}
	@JsonSetter("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}
	@JsonSetter("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonGetter("nationality")
	public String getNationality() {
		return nationality;
	}
	@JsonSetter("nationality")
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@JsonGetter("passportNumber")
	public String getPassportNumber() {
		return passportNumber;
	}
	@JsonSetter("passportNumber")
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@JsonGetter("typeOfPlace")
	public String getTypeOfPlace() {
		return typeOfPlace;
	}
	@JsonSetter("typeOfPlace")
	public void setTypeOfPlace(String typeOfPlace) {
		this.typeOfPlace = typeOfPlace;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PassengerDto)) return false;

		PassengerDto that = (PassengerDto) o;

		if (!getNationality().equals(that.getNationality())) return false;
		if (!getFirstName().equals(that.getFirstName())) return false;
		if (!getLastName().equals(that.getLastName())) return false;
		if (!getPassportNumber().equals(that.getPassportNumber())) return false;
		return getTypeOfPlace().equals(that.getTypeOfPlace());
	}

	@Override
	public int hashCode() {
		int result = getNationality().hashCode();
		result = 31 * result + getFirstName().hashCode();
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + getPassportNumber().hashCode();
		result = 31 * result + getTypeOfPlace().hashCode();
		return result;
	}
}
