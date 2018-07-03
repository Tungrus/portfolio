package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.ClientUpdateDto;

public class ClientUpdateRequest {
	@JsonUnwrapped
	private ClientUpdateDto clientUpdateDto;

	public ClientUpdateRequest(ClientUpdateDto clientUpdateDto) {
		this.clientUpdateDto = clientUpdateDto;
	}

	public ClientUpdateRequest() {
	}

	@JsonGetter("clientUpdateDto")
	public ClientUpdateDto getClientUpdateDto() {
		return clientUpdateDto;
	}

	@JsonSetter("clientUpdateDto")
	public void setClientUpdateDto(ClientUpdateDto clientUpdateDto) {
		this.clientUpdateDto = clientUpdateDto;
	}
}
