package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.UserPostRequest;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Client;

public class UserRegistrationRequestTransformer {

	public static UserPostRequest create(Admin admin) {
		return new UserPostRequest(AdminDtoTransformer.create(admin));
	}

	public static UserPostRequest create(Client client) {
		return new UserPostRequest(ClientDtoTransformer.create(client));
	}
}
