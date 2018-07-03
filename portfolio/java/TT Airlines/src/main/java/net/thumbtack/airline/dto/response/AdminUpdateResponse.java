package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.AdminParamsDto;

public class AdminUpdateResponse {
	@JsonUnwrapped
	private AdminParamsDto adminParamsDto;
	private String userType;

	public AdminUpdateResponse(AdminParamsDto adminParamsDto, String userType) {
		this.adminParamsDto = adminParamsDto;
		this.userType = userType;
	}

	@JsonGetter("adminParamsDto")
	public AdminParamsDto getAdminParamsDto() {
		return adminParamsDto;
	}

	@JsonSetter("adminParamsDto")
	public void setAdminParamsDto(AdminParamsDto adminParamsDto) {
		this.adminParamsDto = adminParamsDto;
	}

	@JsonGetter("userType")
	public String getUserType() {
		return userType;
	}

	@JsonSetter("userType")
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
