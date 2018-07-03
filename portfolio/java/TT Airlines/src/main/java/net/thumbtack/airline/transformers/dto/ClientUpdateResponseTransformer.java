package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.ClientUpdateResponse;
import net.thumbtack.airline.model.Client;

public class ClientUpdateResponseTransformer {
	public static ClientUpdateResponse create(Client client) {
		return new ClientUpdateResponse(ClientUpdateDtoTransformer.create(client));
	}
}
