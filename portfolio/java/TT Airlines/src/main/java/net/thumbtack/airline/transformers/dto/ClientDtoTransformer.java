package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.ClientDto;
import net.thumbtack.airline.dto.ClientParamsDto;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.ClientParams;

public class ClientDtoTransformer {

	public static ClientDto create(Client client) {
		return new ClientDto(client.getLogin(), client.getPassword(), create(client.getClientParams()));
	}

	public static ClientParamsDto create(ClientParams params) {
		return new ClientParamsDto(params.getFirstName(),
				params.getLastName(), params.getPatronymic(), params.getPhoneNumber(), params.getEmail());
	}
}
