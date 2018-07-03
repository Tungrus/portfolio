package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.dto.PlaneInfoDto;

public class AdminGetFlightResponse extends ClientGetFlightResponse {
	@JsonUnwrapped
	private FlightInfoDto flightInfoDto;
	@JsonUnwrapped
	private DateDto dateDto;
	private PlaneInfoDto plane;
	private boolean approved;

	@JsonCreator
	public AdminGetFlightResponse(FlightInfoDto flightInfoDto, PlaneInfoDto planeInfoDto, DateDto dateDto, int flightId,
								  boolean approved) {
		super(flightInfoDto, flightId);
		this.plane = planeInfoDto;
		this.dateDto = dateDto;
		this.approved = approved;
	}

	public PlaneInfoDto getPlane() {
		return plane;
	}

	public void setPlane(PlaneInfoDto plane) {
		this.plane = plane;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public FlightInfoDto getFlightInfoDto() {
		return flightInfoDto;
	}

	public void setFlightInfoDto(FlightInfoDto flightInfoDto) {
		this.flightInfoDto = flightInfoDto;
	}

	public DateDto getDateDto() {
		return dateDto;
	}

	public void setDateDto(DateDto dateDto) {
		this.dateDto = dateDto;
	}
}
