package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.ClientUpdateDto;
import net.thumbtack.airline.model.Client;

public class ClientUpdateDtoTransformer {
	public static ClientUpdateDto create(Client client) {
		return new ClientUpdateDto(client.getPassword(), ClientDtoTransformer.create(client.getClientParams()));
	}
}
