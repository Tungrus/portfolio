package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.ClientDto;

public class ClientRegistrationResponse {
	@JsonUnwrapped
	private ClientDto clientDto;
	private int id;

	@JsonCreator
	public ClientRegistrationResponse(ClientDto clientDto, int id) {
		this.clientDto = clientDto;
		this.id = id;
	}

	@JsonGetter("clientDto")
	public ClientDto getClientDto() {
		return clientDto;
	}

	@JsonSetter("clientDto")
	public void setClientDto(ClientDto clientDto) {
		this.clientDto = clientDto;
	}

	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(int id) {
		this.id = id;
	}
}
