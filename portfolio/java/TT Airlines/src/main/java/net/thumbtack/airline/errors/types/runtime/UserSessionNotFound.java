package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class UserSessionNotFound extends BaseError {

	public UserSessionNotFound() {
		super("SESSION_IS_NOT_EXIST", "javaSessionId", "user must be login to be logout");
	}
}
