package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LogoutResponse {

	@JsonCreator
	public LogoutResponse() {
	}

	@JsonValue
	public String getJsonInstance() {
		return "{}";
	}
}
