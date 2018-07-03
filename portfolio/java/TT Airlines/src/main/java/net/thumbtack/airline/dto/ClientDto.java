package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ClientDto extends UserDto {
	@JsonUnwrapped
	private ClientParamsDto clientParamsDto;

	public ClientDto(String login, String password, ClientParamsDto clientParamsDto) {
		super(login, password);
		this.clientParamsDto = clientParamsDto;
	}

	public ClientDto() {
	}


	public ClientParamsDto getClientParamsDto() {
		return clientParamsDto;
	}

	public void setClientParamsDto(ClientParamsDto clientParamsDto) {
		this.clientParamsDto = clientParamsDto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientDto)) return false;
		if (!super.equals(o)) return false;

		ClientDto clientDto = (ClientDto) o;

		return getClientParamsDto().equals(clientDto.getClientParamsDto());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getClientParamsDto().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "ClientDto{" +
				"clientParamsDto=" + clientParamsDto +
				'}';
	}
}