package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.FlightsQueryDto;

public class FlightsByQueryRequest {
	@JsonUnwrapped
	private FlightsQueryDto query;

	public FlightsByQueryRequest(FlightsQueryDto query) {
		this.query = query;
	}

	public FlightsByQueryRequest() {
	}

	@JsonGetter("query")
	public FlightsQueryDto getQuery() {
		return query;
	}

	@JsonSetter("query")
	public void setQuery(FlightsQueryDto query) {
		this.query = query;
	}
}
