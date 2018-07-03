package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.AdminDto;

public class AdminRegistrationRequest {
	@JsonUnwrapped
	private AdminDto adminDto;

	public AdminRegistrationRequest(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	public AdminRegistrationRequest() {
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

}
