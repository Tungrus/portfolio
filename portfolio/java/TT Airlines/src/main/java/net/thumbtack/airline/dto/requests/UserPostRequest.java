package net.thumbtack.airline.dto.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.dto.UserDto;

public class UserPostRequest<UsrDto extends UserDto> {
	@JsonUnwrapped
	private UsrDto userDto;

	public UserPostRequest(UsrDto adminDTO) {
		this.userDto = adminDTO;
	}

	public UserPostRequest() {
	}

	@JsonSetter("userDto")
	public void setUserDto(UsrDto userDto) {
		this.userDto = userDto;
	}

	@JsonGetter("userDto")
	public UsrDto getUserDto() {
		return userDto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserPostRequest)) return false;

		UserPostRequest<?> that = (UserPostRequest<?>) o;

		return getUserDto().equals(that.getUserDto());
	}

	@Override
	public int hashCode() {
		return getUserDto().hashCode();
	}
}
