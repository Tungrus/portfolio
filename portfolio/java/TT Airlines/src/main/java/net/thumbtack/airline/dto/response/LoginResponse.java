package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

public class LoginResponse {
	@JsonUnwrapped
	private Map<String, Object> userMap;

	public LoginResponse(Map<String, Object> userMap) {
		this.userMap = userMap;
	}
}
