package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.ClientUpdateDto;
import net.thumbtack.airline.dto.requests.ClientUpdateRequest;
import net.thumbtack.airline.model.Client;

public class ClientUpdateRequestTransformer {
	public static ClientUpdateRequest create(Client client, String newPassword) {
		return new ClientUpdateRequest(new ClientUpdateDto(newPassword,
				ClientDtoTransformer.create(client.getClientParams())));
	}
}
