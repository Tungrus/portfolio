package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.ClientRegistrationRequest;
import net.thumbtack.airline.model.Client;

public class ClientRegistrationRequestTransformer {
	public static ClientRegistrationRequest create(Client client) {
		return new ClientRegistrationRequest(ClientDtoTransformer.create(client));
	}
}
