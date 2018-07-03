package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ClientParamsDto extends NameParamsDto {
	private String phoneNumber;
	private String email;

	private String phoneNumberTransformer(String phoneNumber) {
		StringBuilder stringBuilder = new StringBuilder();

		for(String toAdd : phoneNumber.split("-")) {
			stringBuilder.append(toAdd);
		}
		return stringBuilder.toString();
	}

	public ClientParamsDto(String firstName, String lastName, String patronymic, String phoneNumber, String email) {
		super(firstName, lastName, patronymic);
		this.phoneNumber = phoneNumberTransformer(phoneNumber);
		this.email = email;
	}

	public ClientParamsDto() {
	}

	@JsonGetter("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonSetter("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumberTransformer(phoneNumber);
	}

	@JsonGetter("email")
	public String getEmail() {
		return email;
	}

	@JsonSetter("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientParamsDto)) return false;
		if (!super.equals(o)) return false;

		ClientParamsDto that = (ClientParamsDto) o;

		if (!getPhoneNumber().equals(that.getPhoneNumber())) return false;
		return getEmail().equals(that.getEmail());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getPhoneNumber().hashCode();
		result = 31 * result + getEmail().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "ClientParamsDto{" +
				"phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
