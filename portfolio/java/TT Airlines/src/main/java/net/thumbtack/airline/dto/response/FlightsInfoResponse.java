package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.FlightDto;

import java.util.List;

public class FlightsInfoResponse {
	@JsonUnwrapped
	private List<FlightDto> flights;

	@JsonCreator
	public FlightsInfoResponse(List<FlightDto> flights) {
		this.flights = flights;
	}

	public List<FlightDto> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightDto> flights) {
		this.flights = flights;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FlightsInfoResponse)) return false;

		FlightsInfoResponse that = (FlightsInfoResponse) o;

		return getFlights().equals(that.getFlights());
	}

	@Override
	public int hashCode() {
		return getFlights().hashCode();
	}

	@Override
	public String toString() {
		return "FlightsInfoResponseTransformer{" +
				"flights=" + flights +
				'}';
	}
}
