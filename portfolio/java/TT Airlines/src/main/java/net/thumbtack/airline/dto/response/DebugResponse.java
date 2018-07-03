package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class DebugResponse {

	@JsonCreator
	public DebugResponse() {
	}

	@JsonValue
	public String getJsonInstance() {
		return "{}";
	}
}
