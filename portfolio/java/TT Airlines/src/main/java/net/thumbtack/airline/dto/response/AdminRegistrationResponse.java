package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.AdminDto;

public class AdminRegistrationResponse {
	@JsonUnwrapped
	private AdminDto adminDto;
	private int id;

	@JsonCreator
	public AdminRegistrationResponse(AdminDto adminDto, int id) {
		this.adminDto = adminDto;
		this.id = id;
	}

	@JsonGetter("adminDto")
	public AdminDto getAdminDto() {
		return adminDto;
	}

	@JsonSetter("adminDto")
	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(int id) {
		this.id = id;
	}
}
