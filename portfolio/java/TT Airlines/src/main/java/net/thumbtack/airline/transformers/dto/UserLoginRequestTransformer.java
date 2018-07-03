package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.UserLoginRequest;
import net.thumbtack.airline.model.User;

public class UserLoginRequestTransformer {
	public static UserLoginRequest create(User user) {
		return new UserLoginRequest(UserDtoTransformer.create(user));
	}
}
