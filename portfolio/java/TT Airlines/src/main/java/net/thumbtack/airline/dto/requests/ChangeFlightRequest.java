package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightInfoDto;

public class ChangeFlightRequest {
	@JsonUnwrapped
	private DateDto dateDto;
	@JsonUnwrapped
	private FlightInfoDto flightInfoDto;
	private String planeName;

	public ChangeFlightRequest(DateDto dateDto, FlightInfoDto flightInfoDto, String planeName) {
		this.dateDto = dateDto;
		this.flightInfoDto = flightInfoDto;
		this.planeName = planeName;
	}

	public ChangeFlightRequest() {
	}

	@JsonGetter("dateDto")
	public DateDto getDateDto() {
		return dateDto;
	}

	@JsonSetter("dateDto")
	public void setDateDto(DateDto dateDto) {
		this.dateDto = dateDto;
	}

	@JsonGetter("flightInfoDto")
	public FlightInfoDto getFlightInfoDto() {
		return flightInfoDto;
	}

	@JsonSetter("flightInfoDto")
	public void setFlightInfoDto(FlightInfoDto flightInfoDto) {
		this.flightInfoDto = flightInfoDto;
	}

	@JsonGetter("planeName")
	public String getPlaneName() {
		return planeName;
	}

	@JsonSetter("planeName")
	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}
}
