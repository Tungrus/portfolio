package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ClientUpdateDto {
	private String newPassword;
	@JsonUnwrapped
	private ClientParamsDto clientParamsDto;

	public ClientUpdateDto(String newPassword, ClientParamsDto clientParamsDto) {
		this.newPassword = newPassword;
		this.clientParamsDto = clientParamsDto;
	}

	public ClientUpdateDto() {
	}

	@JsonGetter("newPassword")
	public String getNewPassword() {
		return newPassword;
	}

	@JsonSetter("newPassword")
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@JsonGetter("clientParamsDto")
	public ClientParamsDto getClientParamsDto() {
		return clientParamsDto;
	}

	@JsonSetter("clientParamsDto")
	public void setClientParamsDto(ClientParamsDto clientParamsDto) {
		this.clientParamsDto = clientParamsDto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientUpdateDto)) return false;

		ClientUpdateDto that = (ClientUpdateDto) o;

		if (!getNewPassword().equals(that.getNewPassword())) return false;
		return getClientParamsDto().equals(that.getClientParamsDto());
	}

	@Override
	public int hashCode() {
		int result = getNewPassword().hashCode();
		result = 31 * result + getClientParamsDto().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "ClientUpdateDto{" +
				"newPassword='" + newPassword + '\'' +
				", clientParamsDto=" + clientParamsDto +
				'}';
	}
}
