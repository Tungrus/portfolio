package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class DeleteFlightResponse {
	@JsonCreator
	public DeleteFlightResponse() {
	}

	@JsonValue
	public String getJsonInstance() {
		return "{}";
	}
}
