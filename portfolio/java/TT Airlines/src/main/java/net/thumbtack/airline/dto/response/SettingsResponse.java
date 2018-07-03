package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SettingsResponse {
	private int maxNameLength;
	private int maxPasswordLength;

	public SettingsResponse() {
	}

	public SettingsResponse(int maxNameLength, int maxPasswordLength) {
		this.maxNameLength = maxNameLength;
		this.maxPasswordLength = maxPasswordLength;
	}

	@JsonGetter("maxNameLength")
	public int getMaxNameLength() {
		return maxNameLength;
	}
	@JsonSetter("maxNameLength")
	public void setMaxNameLength(int maxNameLength) {
		this.maxNameLength = maxNameLength;
	}

	@JsonGetter("maxPasswordLength")
	public int getMaxPasswordLength() {
		return maxPasswordLength;
	}
	@JsonSetter("maxPasswordLength")
	public void setMaxPasswordLength(int maxPasswordLength) {
		this.maxPasswordLength = maxPasswordLength;
	}
}
