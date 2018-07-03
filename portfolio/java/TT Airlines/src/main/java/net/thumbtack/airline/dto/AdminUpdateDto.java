package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class AdminUpdateDto {
	@JsonUnwrapped
	private AdminParamsDto adminParamsDto;
	private String newPassword;

	public AdminUpdateDto(AdminParamsDto adminParamsDto, String newPassword) {
		this.adminParamsDto = adminParamsDto;
		this.newPassword = newPassword;
	}

	public AdminUpdateDto() {
	}

	@JsonGetter("adminParamsDto")
	public AdminParamsDto getAdminParamsDto() {
		return adminParamsDto;
	}

	@JsonSetter("adminParamsDto")
	public void setAdminParamsDto(AdminParamsDto adminParamsDto) {
		this.adminParamsDto = adminParamsDto;
	}

	@JsonGetter("newPassword")
	public String getNewPassword() {
		return newPassword;
	}

	@JsonSetter("newPassword")
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
