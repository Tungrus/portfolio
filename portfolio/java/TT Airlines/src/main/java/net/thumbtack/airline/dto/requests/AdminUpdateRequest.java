package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.AdminUpdateDto;

public class AdminUpdateRequest {
	@JsonUnwrapped
	private AdminUpdateDto adminUpdateDto;

	public AdminUpdateRequest(AdminUpdateDto adminUpdateDto) {
		this.adminUpdateDto = adminUpdateDto;
	}

	public AdminUpdateRequest() {
	}

	@JsonGetter("adminUpdateDto")
	public AdminUpdateDto getAdminUpdateDto() {
		return adminUpdateDto;
	}

	@JsonSetter("adminUpdateDto")
	public void setAdminUpdateDto(AdminUpdateDto adminUpdateDto) {
		this.adminUpdateDto = adminUpdateDto;
	}
}
