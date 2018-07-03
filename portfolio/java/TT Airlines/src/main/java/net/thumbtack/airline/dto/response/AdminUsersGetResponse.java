package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class AdminUsersGetResponse {
	@JsonUnwrapped
	private List<UserResponse> clients;


	public AdminUsersGetResponse(List<UserResponse> clients) {
		this.clients = clients;
	}

	@JsonGetter
	public List<UserResponse> getClients() {
		return clients;
	}

	@JsonSetter
	public void setClients(List<UserResponse> clients) {
		this.clients = clients;
	}
}
