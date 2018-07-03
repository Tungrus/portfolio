package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.UserDto;

public class UserResponse<U extends UserDto> {
	@JsonUnwrapped
	private U user;
	private int id;

	public UserResponse(U user, int id) {
		this.user = user;
		this.id = id;
	}

	public UserResponse() {
	}

	@JsonGetter("user")
	public U getUser() {
		return user;
	}

	@JsonSetter("user")
	public void setUser(U user) {
		this.user = user;
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
