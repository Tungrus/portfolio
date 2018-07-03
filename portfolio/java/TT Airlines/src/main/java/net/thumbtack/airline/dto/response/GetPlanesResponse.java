package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.PlaneInfoDto;

import java.util.List;

public class GetPlanesResponse {
	@JsonUnwrapped
	private List<PlaneInfoDto> planeInfoDtoList;

	@JsonCreator
	public GetPlanesResponse(List<PlaneInfoDto> planeInfoDtoList) {
		this.planeInfoDtoList = planeInfoDtoList;
	}

	@JsonGetter
	public List<PlaneInfoDto> getPlaneInfoDtoList() {
		return planeInfoDtoList;
	}

	@JsonSetter
	public void setPlaneInfoDtoList(List<PlaneInfoDto> planeInfoDtoList) {
		this.planeInfoDtoList = planeInfoDtoList;
	}
}
