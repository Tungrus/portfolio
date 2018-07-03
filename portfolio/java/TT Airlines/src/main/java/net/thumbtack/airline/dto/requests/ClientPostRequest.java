package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.ClientUpdateDto;

public class ClientPostRequest {
	@JsonUnwrapped
	private ClientUpdateDto clientUpdaterDTO;

	public ClientPostRequest(ClientUpdateDto clientUpdaterDTO) {
		this.clientUpdaterDTO = clientUpdaterDTO;
	}

	public ClientPostRequest() {
	}

	public ClientUpdateDto getClientUpdaterDTO() {
		return clientUpdaterDTO;
	}

	public void setClientUpdaterDTO(ClientUpdateDto clientUpdaterDTO) {
		this.clientUpdaterDTO = clientUpdaterDTO;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientPostRequest)) return false;

		ClientPostRequest that = (ClientPostRequest) o;

		return getClientUpdaterDTO().equals(that.getClientUpdaterDTO());
	}

	@Override
	public int hashCode() {
		return getClientUpdaterDTO().hashCode();
	}

	@Override
	public String toString() {
		return "ClientPostRequest{" +
				"clientUpdaterDTO=" + clientUpdaterDTO +
				'}';
	}
}
