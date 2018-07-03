package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class AlreadyLoginError extends BaseError {
	public AlreadyLoginError() {
		super("LOGIN_ALREADY_EXIST", "register", "user already exist");
	}
}
