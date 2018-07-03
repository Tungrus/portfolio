package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.validation.constraints.NotNull;

public class AdminDto extends UserDto {
	@NotNull
	@JsonUnwrapped
	private AdminParamsDto adminParamsDto;


	public AdminDto(@JsonProperty("register") String login, @JsonProperty("password") String password,
					@JsonProperty("adminParamsDto") AdminParamsDto params) {
		super(login, password);
		this.adminParamsDto = params;
	}

	@JsonGetter("adminParamsDto")
	public AdminParamsDto getAdminParamsDto() {
		return adminParamsDto;
	}

	@JsonSetter("adminParamsDto")
	public void setAdminParamsDto(AdminParamsDto adminParamsDto) {
		this.adminParamsDto = adminParamsDto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdminDto)) return false;
		if (!super.equals(o)) return false;

		AdminDto adminDto = (AdminDto) o;

		return getAdminParamsDto().equals(adminDto.getAdminParamsDto());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getAdminParamsDto().hashCode();
		return result;
	}
}
