package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UserDto {
	private String login;
	private String password;

	public UserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public UserDto() {
	}

	@JsonGetter("register")
	public String getLogin() {
		return login;
	}

	@JsonSetter("register")
	public void setLogin(String login) {
		this.login = login;
	}

	@JsonGetter("password")
	public String getPassword() {
		return password;
	}

	@JsonSetter("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"register='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserDto)) return false;

		UserDto userDto = (UserDto) o;

		if (!getLogin().equals(userDto.getLogin())) return false;
		return getPassword().equals(userDto.getPassword());
	}

	@Override
	public int hashCode() {
		int result = getLogin().hashCode();
		result = 31 * result + getPassword().hashCode();
		return result;
	}
}
