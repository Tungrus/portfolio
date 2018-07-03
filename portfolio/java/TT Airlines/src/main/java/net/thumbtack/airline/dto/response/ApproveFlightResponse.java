package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.dto.PlaneInfoDto;

public class ApproveFlightResponse {
	@JsonUnwrapped
	private DateDto dateDto;
	@JsonUnwrapped
	private FlightInfoDto flightInfoDto;
	private int flightId;
	private boolean approved;
	private PlaneInfoDto planeInfoDto;

	@JsonCreator
	public ApproveFlightResponse(FlightInfoDto flightInfoDto, PlaneInfoDto planeInfoDto, DateDto dateDto,
								 int flightId) {
		this.planeInfoDto = planeInfoDto;
		this.dateDto = dateDto;
		this.flightInfoDto = flightInfoDto;
		this.flightId = flightId;
		this.approved = true;
	}

	public PlaneInfoDto getPlaneInfoDto() {
		return planeInfoDto;
	}

	public void setPlaneInfoDto(PlaneInfoDto planeInfoDto) {
		this.planeInfoDto = planeInfoDto;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public DateDto getDateDto() {
		return dateDto;
	}

	public void setDateDto(DateDto dateDto) {
		this.dateDto = dateDto;
	}

	public FlightInfoDto getFlightInfoDto() {
		return flightInfoDto;
	}

	public void setFlightInfoDto(FlightInfoDto flightInfoDto) {
		this.flightInfoDto = flightInfoDto;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
}
