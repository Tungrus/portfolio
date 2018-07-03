package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.model.User;

public class UserDtoTransformer {
	public static UserDto create(User user) {
		return new UserDto(user.getLogin(), user.getPassword());
	}
}
