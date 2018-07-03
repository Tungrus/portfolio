package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.ClientUpdateDto;

public class ClientUpdateResponse {
	private ClientUpdateDto clientUpdateDto;

	public ClientUpdateResponse(ClientUpdateDto clientUpdateDto) {
		this.clientUpdateDto = clientUpdateDto;
	}

	public ClientUpdateDto getClientUpdateDto() {
		return clientUpdateDto;
	}

	public void setClientUpdateDto(ClientUpdateDto clientUpdateDto) {
		this.clientUpdateDto = clientUpdateDto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientUpdateResponse)) return false;

		ClientUpdateResponse that = (ClientUpdateResponse) o;

		return getClientUpdateDto().equals(that.getClientUpdateDto());
	}

	@Override
	public int hashCode() {
		return getClientUpdateDto().hashCode();
	}

	@Override
	public String toString() {
		return "ClientUpdateResponse{" +
				"clientUpdateDto=" + clientUpdateDto +
				'}';
	}
}
