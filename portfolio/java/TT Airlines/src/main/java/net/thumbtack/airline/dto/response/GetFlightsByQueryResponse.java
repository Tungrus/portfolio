package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class GetFlightsByQueryResponse {
	@JsonUnwrapped
	private List<ClientGetFlightResponse> flights;

	public GetFlightsByQueryResponse(List<ClientGetFlightResponse> flights) {
		this.flights = flights;
	}

	public List<ClientGetFlightResponse> getFlights() {
		return flights;
	}

	public void setFlights(List<ClientGetFlightResponse> flights) {
		this.flights = flights;
	}
}
