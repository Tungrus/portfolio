package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.ClientDto;

public class ClientRegistrationRequest {
	@JsonUnwrapped
	private ClientDto clientDto;

	public ClientRegistrationRequest() {
	}

	public ClientRegistrationRequest(ClientDto clientDto) {
		this.clientDto = clientDto;
	}

	@JsonGetter("clientDto")
	public ClientDto getClientDto() {
		return clientDto;
	}

	@JsonSetter("clientDto")
	public void setClientDto(ClientDto clientDto) {
		this.clientDto = clientDto;
	}
}
