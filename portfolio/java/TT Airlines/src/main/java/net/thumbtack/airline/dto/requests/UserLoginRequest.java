package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.UserDto;

public class UserLoginRequest {
	@JsonUnwrapped
	private UserDto userDto;


	public UserLoginRequest(UserDto userDto) {
		this.userDto = userDto;
	}

	public UserLoginRequest() {
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
}
