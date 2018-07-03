package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.AdminRegistrationResponse;
import net.thumbtack.airline.dto.response.AdminUsersGetResponse;
import net.thumbtack.airline.dto.response.UserResponse;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Client;

import java.util.ArrayList;
import java.util.List;

public class UserResponseTransformer {
	public static AdminRegistrationResponse create(Admin admin) {
		return new AdminRegistrationResponse(AdminDtoTransformer.create(admin), admin.getId());
	}

	public static UserResponse create(Client user) {
		return new UserResponse(ClientDtoTransformer.create(user), user.getId());
	}

	public static AdminUsersGetResponse create(List<Client> clients) {
		List result = new ArrayList<UserResponse>();
		for (Client client : clients) {
			result.add(create(client));
		}
		return new AdminUsersGetResponse(result);
	}

	public static UserResponse createUserResponse(Admin admin) {
		return new UserResponse(AdminDtoTransformer.create(admin), admin.getId());
	}

	public static UserResponse createUserResponse(Client client) {
		return new UserResponse(ClientDtoTransformer.create(client), client.getId());
	}
}
